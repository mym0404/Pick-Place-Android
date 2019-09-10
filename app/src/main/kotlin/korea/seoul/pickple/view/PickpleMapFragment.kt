package korea.seoul.pickple.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import korea.seoul.pickple.R
import korea.seoul.pickple.common.util.MapUtil
import korea.seoul.pickple.data.api.DirectionsResponse
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.DirectionsRepository
import korea.seoul.pickple.databinding.FragmentPickpleMapBinding
import korea.seoul.pickple.ui.course.map.MapActivity
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

final class PickpleMapFragment  : Fragment() {

    //region Controller
    inner class PickpleMapController {

        fun updateLocationAndZoomScale(places : List<Place>) {
            this@PickpleMapFragment.updateMapPositionAndScale(places)
        }

    }

    fun getController() : PickpleMapController = this.PickpleMapController()
    //endregion

    private val TAG = PickpleMapFragment::class.java.simpleName

    private var _mBinding : FragmentPickpleMapBinding? = null
    private val mBinding : FragmentPickpleMapBinding
        get() = _mBinding!!


    /**
     * [GoogleMap] Instance
     */
    private var mMap: GoogleMap? = null

    /**
     * Because of Async [GoogleMap] retriving routine
     */
    private val pendingTasks: MutableList<() -> Unit> = mutableListOf()

    private val mapUtil: MapUtil by inject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return FragmentPickpleMapBinding.inflate(inflater,container,false).also { _mBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.lifecycleOwner = viewLifecycleOwner


        initMap()
    }

    /**
     * [GoogleMap] Initialization from [SupportMapFragment] in [MapActivity]
     *
     * @see GoogleMap
     * @see SupportMapFragment
     * @see mMap
     */
    private fun initMap() {
        val mapFrag = SupportMapFragment.newInstance()
        mapFrag.getMapAsync {map->
            //INIT MAP
            mMap = map
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            map.isMyLocationEnabled = true
            map.isBuildingsEnabled = true
            map.isTrafficEnabled = true
            map.isIndoorEnabled = true


            adjustMapLocation(map)


            pendingTasks.forEach { it.invoke() }
            pendingTasks.clear()
        }

        childFragmentManager.beginTransaction().add(R.id.root_container,mapFrag).commit()
    }

    private fun adjustMapLocation(map: GoogleMap) {

    }


    /**
     * 맵의 위치와 줌 정도를 위치들에 맞춰서 재조정한다.
     *
     * Marker도 찍는다.
     *
     * Polygon도 만든다.
     */
    private fun updateMapPositionAndScale(places: List<Place>) {

        val runnable = {

            //Clear Markers
            mMap?.clear()
            //Add Markers
            places.map { place ->
                place.location?.let { location ->
                    mMap?.addMarker(MarkerOptions().position(location.toLatLng()))
                }
            }
            //Move Camera To Center of Places
            mMap?.moveCamera(mapUtil.autoZoomLevel(places))

            val repo: DirectionsRepository = get()

            for (i in 0 until places.size - 1) {
                //TODO 막코딩
                repo.getRouteFromTwoPlace(
                    places[i].location!!,
                    places[i+1].location!!,
                    getString(R.string.google_maps_key)
                ).enqueue(object : Callback<DirectionsResponse> {
                    override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                        Log.e(TAG, t.toString())
                    }

                    override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {

                        val r = response.body()!!

                        val points = PolyUtil.decode(r.routes[0].overviewPolyline.points)
                        Log.e(TAG, points.toString())

                        val option = PolylineOptions().color(Color.CYAN).jointType(JointType.ROUND).visible(true).zIndex(50f).width(10f).add(*points.toTypedArray())
                        mMap?.addPolyline(option)

                    }
                })
            }


        }
        if(mMap == null)
            pendingTasks.add(runnable)
        else
            runnable()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _mBinding = null
    }

}