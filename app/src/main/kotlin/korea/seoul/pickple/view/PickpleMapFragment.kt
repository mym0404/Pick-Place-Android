package korea.seoul.pickple.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.PolyUtil
import korea.seoul.pickple.common.util.MapUtil
import korea.seoul.pickple.common.util.PermissionDexterUtil
import korea.seoul.pickple.common.util.PermissionListener
import korea.seoul.pickple.data.api.response.directions.DirectionsResponse
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.interfaces.DirectionsRepository
import korea.seoul.pickple.databinding.FragmentPickpleMapBinding
import korea.seoul.pickple.ui.course.map.MapActivity
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


final class PickpleMapFragment : Fragment() {

    //region Controller
    inner class PickpleMapController {

        /**
         * 전달한 [Location] 객체로 현재 지도를 이동시킨다.
         *
         * @param location 이동할 위치
         * @param animate 애니메이션을 하며 이동할 것인지, 그냥 이동할 것인지 결정
         */
        fun setLocation(location: Location, animate: Boolean = false) {
            tryMapRunnable {
                if (animate) {
                    mMap?.animateCamera(CameraUpdateFactory.newLatLng(location.toLatLng()))
                } else {
                    mMap?.moveCamera(CameraUpdateFactory.newLatLng(location.toLatLng()))
                }
            }
        }

        /**
         * 전달한 zoom 정도로 지도의 scale 을 조정한다.
         *
         * @param zoom 지도의 scale 정도
         */
        fun setZoom(zoom: Float, animate : Boolean = false) {
            tryMapRunnable {
                if(animate) {
                    mMap?.animateCamera(CameraUpdateFactory.zoomTo(zoom))
                }else {
                    mMap?.moveCamera(CameraUpdateFactory.zoomTo(zoom))
                }
            }
        }


        /**
         * 전달한 [Place]의 리스트들을 이용해서 전체적인 모습을 볼 수 있게 해준다.
         *
         * 1. 현재 지도의 위치와 Zoom 정도가 조절된다.
         *
         * 2. 지정된 장소에 마커가 찍힌다.
         *
         * 3. 전달한 [places] 리스트의 [Place] 객체의 순서대로 지도에 경로가 Polyline으로 표시된다.
         *
         * @param places 장소들
         * @param drawPolyline 장소들을 잇는 선을 그을 것인지
         */
        fun updateLocationAndZoomScale(places: List<Place>,drawPolyline : Boolean = false) {
            tryMapRunnable {
                updateMapPositionAndScale(places,drawPolyline)
            }
        }

        /**
         * 내 위치로 설정
         *
         * 비동기로 실행됨 주의
         */
        fun setMyLocationAsync() {
            tryMapRunnable {

                fusedClient.lastLocation.addOnSuccessListener { location : android.location.Location? ->

                    location?.let {location->
                        mMap?.moveCamera(CameraUpdateFactory.newLatLng(LatLng(location.latitude,location.longitude)))
                    }

                }
            }
        }

        /**
         * 마커를 클릭했을 때 마커를 반환해주는 콜백 등록
         */
        fun setMarkerClickedListener(callback : (marker : Marker) -> Unit) {
            this@PickpleMapFragment.markerClickListener = callback

        }

        fun addMarker(place : Place) {
            if(place.location == null) return

            addMarker(place.location!!)
        }
        fun addMarker(location : Location) {
            addMarker(location.toLatLng())
        }
        fun addMarker(latlng : LatLng) {
            tryMapRunnable {
                mMap?.addMarker(MarkerOptions().position(latlng))
            }
        }

    }

    fun getController(): PickpleMapController = this.PickpleMapController()


    private fun tryMapRunnable(runnable: Runnable) {
        tryMapRunnable { runnable.run() }
    }

    private fun tryMapRunnable(runnable: () -> Unit) {
        if (mMap == null)
            pendingTasks.add(runnable)
        else
            runnable()
    }


    //endregion

    private val TAG = PickpleMapFragment::class.java.simpleName

    private var _mBinding: FragmentPickpleMapBinding? = null
    private val mBinding: FragmentPickpleMapBinding
        get() = _mBinding!!

    private val dexterUtil: PermissionDexterUtil by inject()

    /**
     * [GoogleMap] Instance
     */
    private var mMap: GoogleMap? = null

    /**
     * Because of Async [GoogleMap] retriving routine
     */
    private val pendingTasks: MutableList<() -> Unit> = mutableListOf()

    private val mapUtil: MapUtil by inject()

    private lateinit var fusedClient : FusedLocationProviderClient

    private var markerClickListener : ((Marker) -> Unit)? = null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return FragmentPickpleMapBinding.inflate(inflater, container, false).also { _mBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.lifecycleOwner = viewLifecycleOwner

        fusedClient = FusedLocationProviderClient(this.context!!)

        dexterUtil.requestPermissions(this.activity!!, object : PermissionListener {
            override fun onPermissionGranted() {
                //Add MapFragment
                initMap()
            }

            override fun onPermissionShouldBeGranted(deniedPermissions: List<String>) {
            }

            override fun onAnyPermissionsPermanentlyDeined(deniedPermissions: List<String>, permanentDeniedPermissions: List<String>) {
            }
        }, mutableListOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION))



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
        mapFrag.getMapAsync { map ->
            //INIT MAP
            mMap = map
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            map.isMyLocationEnabled = true
            map.isBuildingsEnabled = true
            map.isTrafficEnabled = true
            map.isIndoorEnabled = true



            mMap?.setOnMarkerClickListener {marker->
                this@PickpleMapFragment.markerClickListener?.invoke(marker)
                true
            }


            pendingTasks.forEach { it.invoke() }
            pendingTasks.clear()


        }

        childFragmentManager.beginTransaction().add(korea.seoul.pickple.R.id.root_container, mapFrag).commit()
    }


    /**
     * 맵의 위치와 줌 정도를 위치들에 맞춰서 재조정한다.
     *
     * Marker도 찍는다.
     *
     * Polygon도 만든다.
     */
    private fun updateMapPositionAndScale(places: List<Place>, drawPolyline: Boolean) {


        //Clear Markers
        mMap?.clear()
        //Add Markers
        places.map { place ->
            place.location?.let { location ->
                mMap?.addMarker(MarkerOptions().position(location.toLatLng()))
            }
        }


        //Move Camera To Center of Places
        mapUtil.autoZoomLevel(places)?.run {
            try {
                mMap?.moveCamera(this)
            }catch (t : Throwable){}
        }

        if(drawPolyline) {
            val repo: DirectionsRepository = get()

            for (i in 0 until places.size - 1) {
                //TODO 막코딩
                repo.getRouteFromTwoPlace(
                    places[i].location!!,
                    places[i + 1].location!!,
                    getString(korea.seoul.pickple.R.string.google_maps_key)
                ).enqueue(object : Callback<DirectionsResponse> {
                    override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                        Log.e(TAG, t.toString())
                    }

                    override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {

                        try {
                            val r = response.body()!!

                            val points = PolyUtil.decode(r.routes[0].overviewPolyline.points)

                            val option = PolylineOptions().color(Color.CYAN).jointType(JointType.ROUND).visible(true).zIndex(50f).width(10f).add(*points.toTypedArray())
                            mMap?.addPolyline(option)
                        } catch (t: Throwable) {
                            Log.e(TAG, "fail to add polyline")
                        }

                    }
                })
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()

        _mBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        markerClickListener = null
    }

}