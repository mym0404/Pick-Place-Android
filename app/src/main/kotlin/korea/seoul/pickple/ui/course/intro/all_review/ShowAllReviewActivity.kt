package korea.seoul.pickple.ui.course.intro.all_review

import android.os.Bundle
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityShowAllReviewBinding
import korea.seoul.pickple.ui.BaseFragment
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import korea.seoul.pickple.ui.course.intro.review.ReviewFragment
import kotlinx.android.synthetic.main.fragment_review.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

// ReviewFragment를 만들 때, 공통된 ViewModel을 가지고 싶어서 Fragment로 변경해서 진
class ShowAllReviewActivity : BaseFragment<ActivityShowAllReviewBinding>(R.layout.activity_show_all_review) {
    private var mIsCourseReview = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            mIsCourseReview = getBoolean(IS_COURSE_REVIEW)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {

            mBinding.apply {
                supportFragmentManager.beginTransaction().apply {
                    disallowAddToBackStack()
                    replace(R.id.placeShowAllReviewReview, ReviewFragment.newInstance(mIsCourseReview, true))
                }.commit()

                btnShowAllReviewClose.setOnClickListener {
                    // ShowAllReview를 종료하자.
                    supportFragmentManager.popBackStack()
                }
            }
        }
    }

    companion object {
        const val IS_COURSE_REVIEW = "IS_COURSE_REVIEW"

        @JvmStatic
        fun newInstance(isCourseReview: Boolean) : ShowAllReviewActivity {
            return ShowAllReviewActivity().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_COURSE_REVIEW, isCourseReview)
                }
            }
        }
    }
}
