package korea.seoul.pickple.ui.navigation.course

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.common.widget.observeOnce
import korea.seoul.pickple.databinding.ActivityNavigationCourseBinding
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.navigate
import korea.seoul.pickple.ui.parseIntent
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * Created by mj on 23, September, 2019
 */

class NavigationCourseActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityNavigationCourseBinding

    private lateinit var mViewModel : NavigationCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNavigationCourseBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        val arg = parseIntent(intent)
        mViewModel = getViewModel()


        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        initView()
        observeViewModel()
    }

    private fun initView() {

        mBinding.recyclerView.apply {
            adapter = NavigationCourseAdapter(mViewModel)

        }

        mBinding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeViewModel() {
        mViewModel.apply {
            clickCourse.observeOnce(this@NavigationCourseActivity) {
                navigate(this@NavigationCourseActivity, NavigationArgs.CourseIntroArg(it.courseIdx))
            }
        }
    }
}