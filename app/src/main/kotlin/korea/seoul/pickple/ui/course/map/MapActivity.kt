package korea.seoul.pickple.ui.course.map

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.setShowSideItemsWithDimens
import korea.seoul.pickple.common.util.MapUtil
import korea.seoul.pickple.common.util.PermissionDexterUtil
import korea.seoul.pickple.common.util.PermissionListener
import korea.seoul.pickple.data.api.DirectionsResponse
import korea.seoul.pickple.data.api.GeocodingResponse
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.DirectionsRepository
import korea.seoul.pickple.databinding.ActivityMapBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

/**
 * Activity for Course Google Map API representation
 *
 * @author MJ
 */
class MapActivity : AppCompatActivity() {

    /**
     * TAG
     */
    private val TAG = MapActivity::class.java.simpleName

    /**
     * Data Binding
     */
    private lateinit var mBinding: ActivityMapBinding
    /**
     * ViewModel from Koin
     */
    private val mViewModel: MapViewModel by viewModel(MapViewModel::class, null) { parametersOf(1000) }
    /**
     * [GoogleMap] Instance
     */
    private lateinit var mMap: GoogleMap

    private val mapUtil: MapUtil by inject()
    private val dexterUtil : PermissionDexterUtil by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMapBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        dexterUtil.requestPermissions(this,object : PermissionListener {
            override fun onPermissionGranted() {
            }

            override fun onPermissionShouldBeGranted(deniedPermissions: List<String>) {
            }

            override fun onAnyPermissionsPermanentlyDeined(deniedPermissions: List<String>, permanentDeniedPermissions: List<String>) {
            }
        },mutableListOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION))

        initMap()
        initMapPager()
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
        val mapFrag = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFrag.getMapAsync {
            mMap = it
            mMap.isMyLocationEnabled = true
            mMap.isBuildingsEnabled = true
            adjustMapLocation(mMap)
        }
    }

    private fun adjustMapLocation(map: GoogleMap) {
    }

    /**
     * Initialization ViewPager2
     */
    private fun initMapPager() {
        mBinding.viewPager2.apply {
            adapter = MapPagerAdapter()
            setShowSideItemsWithDimens(
                R.dimen.map_pager_page_margin,
                R.dimen.map_pager_pager_offset
            )
        }
    }

    /**
     * Observe [LiveData]s of [ViewModel]
     */
    private fun observeViewModel() {
        mViewModel.apply {
            this.places.observe(this@MapActivity, Observer { places ->

                updateMapPositionAndScale(places)

                (mBinding.viewPager2.adapter as? MapPagerAdapter)?.let { adapter ->
                    adapter.items = places
                    adapter.notifyDataSetChanged()
                }

            })
        }
    }

    private fun updateMapPositionAndScale(places: List<Place>) {

        //TODO Dirty Thread coding
        thread {
            Thread.sleep(1000)
            runOnUiThread {
                //Clear Markers
//                mMap.clear()
                //Add Markers
                places.map { place ->
                    place.location?.let { location ->
                        mMap.addMarker(MarkerOptions().position(location.toLatLng()))
                    }
                }
                //Move Camera To Center of Places
                mMap.moveCamera(mapUtil.autoZoomLevel(places))
            }
        }

        val repo : DirectionsRepository = get()

        //TODO 막코딩
        repo.getRouteFromTwoPlace(
            places[0].location!!,
            places[1].location!!,
            getString(R.string.google_maps_key)).enqueue(object : Callback<DirectionsResponse> {
            override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                Log.e(TAG,t.toString())
            }

            override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {

                val r = response.body()!!

                val points = PolyUtil.decode(r.routes[0].overviewPolyline.points)
                Log.e(TAG,points.toString())

                val option = PolylineOptions().color(Color.CYAN).jointType(JointType.ROUND).visible(true).zIndex(50f).width(10f).add(*points.toTypedArray())
                mMap.addPolyline(option)

            }
        })
        repo.getRouteFromTwoPlace(
            places[1].location!!,
            places[2].location!!,
            getString(R.string.google_maps_key)).enqueue(object : Callback<DirectionsResponse> {
            override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                Log.e(TAG,t.toString())
            }

            override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {

                val r = response.body()!!

                val points = PolyUtil.decode(r.routes[0].overviewPolyline.points)
                Log.e(TAG,points.toString())

                val option = PolylineOptions().color(Color.GREEN).jointType(JointType.ROUND).visible(true).width(10f).zIndex(30f).add(*points.toTypedArray())
                mMap.addPolyline(option)
            }
        })
    }


}
