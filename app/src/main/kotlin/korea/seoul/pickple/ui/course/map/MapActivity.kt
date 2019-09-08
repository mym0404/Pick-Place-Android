package korea.seoul.pickple.ui.course.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.setShowSideItemsWithDimens
import korea.seoul.pickple.common.util.MapUtil
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMapBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

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
            //TODO Permission add
//            mMap.isMyLocationEnabled = true
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
                mMap.clear()
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

//        repo.getGeocoding(places.first().location!!,getString(R.string.google_maps_key)).enqueue(object : Callback<GeocodingResponse> {
//            override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
//                Log.e(TAG,t.toString())
//            }
//
//            override fun onResponse(call: Call<GeocodingResponse>, response: Response<GeocodingResponse>) {
//                Log.e(TAG,response.toString())
//                Log.e(TAG,response.isSuccessful.toString())
//                Log.e(TAG,response.errorBody().toString())
//                Log.e(TAG,response.body()?.toString())
//            }
//        })

        repo.getRouteFromTwoPlace(
            places.first().location!!,
            places.last().location!!,
            getString(R.string.google_maps_key),
            places.drop(1).dropLast(1).map { it.location!! }).enqueue(object : Callback<DirectionsResponse> {
            override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                Log.e(TAG,t.toString())
            }

            override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {
                Log.e(TAG,response.toString())
                Log.e(TAG,response.isSuccessful.toString())
                Log.e(TAG,response.errorBody().toString())
                Log.e(TAG,response.body()?.toString())
            }
        })
    }


}
