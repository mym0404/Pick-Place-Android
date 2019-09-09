package korea.seoul.pickple.ui.course.map

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.setShowSideItemsWithDimens
import korea.seoul.pickple.common.util.PermissionDexterUtil
import korea.seoul.pickple.common.util.PermissionListener
import korea.seoul.pickple.databinding.ActivityMapBinding
import korea.seoul.pickple.view.PickpleMapFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

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

    private val dexterUtil: PermissionDexterUtil by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMapBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        dexterUtil.requestPermissions(this, object : PermissionListener {
            override fun onPermissionGranted() {
            }

            override fun onPermissionShouldBeGranted(deniedPermissions: List<String>) {
            }

            override fun onAnyPermissionsPermanentlyDeined(deniedPermissions: List<String>, permanentDeniedPermissions: List<String>) {
            }
        }, mutableListOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION))


        //Add MapFragment
        this.supportFragmentManager.beginTransaction().add(R.id.map_container, PickpleMapFragment()).commitNow()

        initMapPager()
        observeViewModel()
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

    private fun observeViewModel() {
        mViewModel.apply {

            this.places.observe(this@MapActivity, Observer { places ->
                (mBinding.viewPager2.adapter as? MapPagerAdapter)?.let { adapter ->
                    adapter.items = places
                    adapter.notifyDataSetChanged()
                }
            })
        }

    }


}
