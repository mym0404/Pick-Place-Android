package korea.seoul.pickple.ui.course.map

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.setShowSideItemsWithDimens
import korea.seoul.pickple.common.util.MapUtil
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ActivityMapBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
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
    private val mViewModel: MapViewModel by viewModel()

    /**
     * [GoogleMap] Instance
     */
    private lateinit var mMap: GoogleMap

    private val mapUtil : MapUtil by inject()


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

                (mBinding.viewPager2.adapter as? MapPagerAdapter)?.let {adapter->
                    adapter.items = places
                    adapter.notifyDataSetChanged()
                }

            })
        }
    }

    private fun updateMapPositionAndScale(places : List<Place>) {

        thread {
            Thread.sleep(1000)
            runOnUiThread {
                mMap.clear()
                places.map {
                    it.location?.let {
                        mMap.addMarker(MarkerOptions().position(LatLng(it.latitude,it.longitude)))
                    }
                }

                mMap.moveCamera(mapUtil.autoZoomLevel(places))
            }
        }
    }


}
