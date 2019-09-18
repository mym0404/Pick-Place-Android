package korea.seoul.pickple.ui.course.intro.simple_intro

import android.os.Bundle
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityCourseSimpleIntroBinding
import korea.seoul.pickple.ui.BaseFragment
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * 코스소개 - 첫화면 을 구현한 화면이다.
 *
 * @author greedy0110
 * */
class CourseSimpleIntroActivity : BaseFragment<ActivityCourseSimpleIntroBinding>(R.layout.activity_course_simple_intro) {
    private val mCourseIntroViewModel: CourseIntroViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            mBinding.courseViewModel = mCourseIntroViewModel
        }
    }
}
