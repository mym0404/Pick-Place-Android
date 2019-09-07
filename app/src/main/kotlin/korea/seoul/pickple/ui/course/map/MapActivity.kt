package korea.seoul.pickple.ui.course.map

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.databinding.ActivityMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapActivity : AppCompatActivity() {

    private val TAG = MapActivity::class.java.simpleName

    private lateinit var mBinding : ActivityMapBinding
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
        }
    }


}
