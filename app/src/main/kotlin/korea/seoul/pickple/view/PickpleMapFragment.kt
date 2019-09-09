package korea.seoul.pickple.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
import korea.seoul.pickple.ui.course.map.MapViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PickpleMapFragment  : Fragment() {

    private val TAG = PickpleMapFragment::class.java.simpleName

    private var _mBinding : FragmentPickpleMapBinding? = null
    private val mBinding : FragmentPickpleMapBinding
        get() = _mBinding!!


    private val mViewModel : MapViewModel by sharedViewModel()

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
        observeViewModel()
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
            mMap = map
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            adjustMapLocation(map)



        }
        //Map Add
        childFragmentManager.beginTransaction().add(R.id.root_container,mapFrag,mapFrag.tag).commit()

        mapFrag.getMapAsync {
            mMap = it
            mMap!!.isMyLocationEnabled = true
            mMap!!.isBuildingsEnabled = true
            adjustMapLocation(mMap!!)

            pendingTasks.forEach { it() }
            pendingTasks.clear()
        }
    }

    private fun adjustMapLocation(map: GoogleMap) {

    }



    /**
     * Observe [LiveData]s of [ViewModel]
     */
    private fun observeViewModel() {
        mViewModel.apply {
            this.places.observe(viewLifecycleOwner, Observer { places ->

                if(mMap == null)
                    pendingTasks += {updateMapPositionAndScale(places)}
                else
                    updateMapPositionAndScale(places)

            })
        }
    }

    private fun updateMapPositionAndScale(places: List<Place>) {



        //TODO Dirty Thread coding
        //Clear Markers
//                mMap.clear()
        //Add Markers
        places.map { place ->
            place.location?.let { location ->
                mMap?.addMarker(MarkerOptions().position(location.toLatLng()))
            }
        }
        //Move Camera To Center of Places
        mMap?.moveCamera(mapUtil.autoZoomLevel(places))

        val repo: DirectionsRepository = get()

        //TODO 막코딩
        repo.getRouteFromTwoPlace(
            places[0].location!!,
            places[1].location!!,
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
        repo.getRouteFromTwoPlace(
            places[1].location!!,
            places[2].location!!,
            getString(R.string.google_maps_key)
        ).enqueue(object : Callback<DirectionsResponse> {
            override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {

                val r = response.body()!!

                val points = PolyUtil.decode(r.routes[0].overviewPolyline.points)
                Log.e(TAG, points.toString())

                val option = PolylineOptions().color(Color.GREEN).jointType(JointType.ROUND).visible(true).width(10f).zIndex(30f).add(*points.toTypedArray())
                mMap?.addPolyline(option)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _mBinding = null
    }

}