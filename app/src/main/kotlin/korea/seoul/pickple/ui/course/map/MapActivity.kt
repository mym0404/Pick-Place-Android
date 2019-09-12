package korea.seoul.pickple.ui.course.map

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.setShowSideItemsWithDimens
import korea.seoul.pickple.databinding.ActivityMapBinding
import korea.seoul.pickple.view.PickpleMapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.ref.WeakReference

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


    private var mMapFragment: WeakReference<PickpleMapFragment?> = WeakReference(null)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMapBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)


        if (mMapFragment.get() == null) {
            val frag = PickpleMapFragment()

            mMapFragment = WeakReference(frag)
            this@MapActivity.supportFragmentManager.beginTransaction().add(R.id.map_container, frag).commit()
        }






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

                mMapFragment.get()?.getController()?.updateLocationAndZoomScale(places)
            })
        }

    }


}
