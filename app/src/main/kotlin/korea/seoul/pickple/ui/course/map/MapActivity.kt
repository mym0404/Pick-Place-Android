package korea.seoul.pickple.ui.course.map

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.setShowSideItemsWithDimens
import korea.seoul.pickple.databinding.ActivityMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    private lateinit var mBinding : ActivityMapBinding
    /**
     * ViewModel from Koin
     */
    private val mViewModel : MapViewModel by viewModel()

    /**
     * [GoogleMap] Instance
     */
    private lateinit var mMap : GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMapBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        initMap()
        initMapPager()
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
            adjustMapLocation(mMap)
        }
    }

    private fun adjustMapLocation(map : GoogleMap) {

    }

    /**
     * Initialization ViewPager2
     */
    private fun initMapPager() {
        mBinding.viewPager2.apply {
            adapter = MapPagerAdapter()
            setShowSideItemsWithDimens(R.dimen.map_pager_page_margin,R.dimen.map_pager_pager_offset)
        }
    }


}
