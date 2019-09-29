package korea.seoul.pickple.ui.course.intro.unite_intro

import android.net.Uri
import android.os.Bundle
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityUniteCourseBinding
import korea.seoul.pickple.ui.BaseFragment
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import korea.seoul.pickple.ui.course.intro.review.ReviewFragment
import korea.seoul.pickple.ui.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * 코스소개 - 묶음소개 를 구현한 화면이다.
 *
 * (반드시 넘겨야한다.) courseId : 우리가 선택한 코스의 아이디 정보
 * @author greedy0110
 * */
class UniteCourseActivity : BaseFragment<ActivityUniteCourseBinding>(R.layout.activity_unite_course) {
    private val mCourseIntroViewModel: CourseIntroViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            supportFragmentManager.beginTransaction().apply {
                disallowAddToBackStack()
                replace(R.id.placeUniteCourseReviewFragment
                    ,ReviewFragment.newInstance(true),
                    null)
            }.commit()

            mBinding.viewModel = mCourseIntroViewModel
            mBinding.btnUniteCourseMap.setOnClickListener {
                // Map 을 화면에 띄워주자. (course를 함께 건내야 한다.)
                navigate(
                    curActivity = this,
                    arg = NavigationArgs.MapActivityArg(mCourseIntroViewModel.course.value)
                )
            }

            mBinding

            mBinding.btnUniteCourseCourseDetail.setOnClickListener {
                mCourseIntroViewModel.course.value?.let { course ->
                    navigate(
                        curActivity = this,
                        arg = NavigationArgs.CourseCreateArgs(
                            title = course.name,
                            thumbnail = Uri.parse(course.thumbnail),
                            description = course.description ?: "",
                            tagList = course.tagList?: listOf(),
                            onlyShow = true,
                            course = course.id
                        )
                    )
                }

            }
        }
    }
}
