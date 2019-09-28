package korea.seoul.pickple.ui.navigation.pickplace

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.common.util.LinearItemDecoration
import korea.seoul.pickple.common.widget.observeOnce
import korea.seoul.pickple.databinding.ActivityNavigationPickPlaceBinding
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.navigate
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by mj on 27, September, 2019
 */

class NavigationPickPlaceActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityNavigationPickPlaceBinding

    private val mViewModel : NavigationPickPlaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNavigationPickPlaceBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        mBinding.recyclerViewOragage.apply {
            adapter = NavigationPickPlaceAdapter(mViewModel)
            addItemDecoration(LinearItemDecoration(this@NavigationPickPlaceActivity,16))
        }
        mBinding.recyclerViewTraditional.apply {
            adapter = NavigationPickPlaceAdapter(mViewModel)
            addItemDecoration(LinearItemDecoration(this@NavigationPickPlaceActivity,16))
        }
        mBinding.recyclerCustom.apply {
            adapter = NavigationPickPlaceAdapter(mViewModel)
            addItemDecoration(LinearItemDecoration(this@NavigationPickPlaceActivity,16))
        }

        mBinding.backButton.setOnClickListener {
            onBackPressed()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        mViewModel.apply {
            clickCourse.observeOnce(this@NavigationPickPlaceActivity) {
                navigate(this@NavigationPickPlaceActivity,NavigationArgs.CourseIntroArg(it.courseIdx))
            }
        }
    }
}