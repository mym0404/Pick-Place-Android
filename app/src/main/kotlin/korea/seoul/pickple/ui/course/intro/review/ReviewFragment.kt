package korea.seoul.pickple.ui.course.intro.review


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.FragmentReviewBinding
import korea.seoul.pickple.ui.BaseFragment
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {
    private lateinit var mReviewAdapter: ReviewAdapter
    private val mCourseIntroViewModel: CourseIntroViewModel by sharedViewModel()
    private var mIsCourseReview: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.run {
            mIsCourseReview = getBoolean(ARG_IS_COURSE_REVIEW, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            mReviewAdapter = ReviewAdapter(this, listOf())
            mBinding.courseIntroViewModel = mCourseIntroViewModel
            mBinding.isCourseReview = mIsCourseReview
            mBinding.rvReviewList.apply {
                adapter = mReviewAdapter
                layoutManager = LinearLayoutManager(this@run)
            }
        }
    }

    companion object {
        const val ARG_IS_COURSE_REVIEW = "isCourseReview"

        @JvmStatic
        fun newInstance(isCourseReview: Boolean): ReviewFragment {
            return ReviewFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_COURSE_REVIEW, isCourseReview)
                }
            }
        }
    }
}
