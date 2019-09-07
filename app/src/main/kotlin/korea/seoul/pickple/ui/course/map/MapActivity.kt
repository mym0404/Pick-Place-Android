package korea.seoul.pickple.ui.course.map

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.setShowSideItemsWithDimens
import korea.seoul.pickple.databinding.ActivityMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMapBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)


        initMapPager()
    }

    /**
     * ViewPager2 를 초기화하는 메서드
     */
    private fun initMapPager() {
        mBinding.viewPager2.apply {
            adapter = MapPagerAdapter()
            setShowSideItemsWithDimens(R.dimen.map_pager_page_margin,R.dimen.map_pager_pager_offset)
        }
    }


}
