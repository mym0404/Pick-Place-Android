package korea.seoul.pickple.ui.course.intro

import android.os.Bundle
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityCourseIntroBinding
import korea.seoul.pickple.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CourseIntroActivity : BaseActivity<ActivityCourseIntroBinding>(R.layout.activity_course_intro) {
    private val viewModel: CourseIntroViewModel by viewModel { parametersOf(0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.vpCourseIntro.adapter = CourseIntroViewPagerAdapter(
            supportFragmentManager
        )

        viewModel.courseId = 0
    }
}
