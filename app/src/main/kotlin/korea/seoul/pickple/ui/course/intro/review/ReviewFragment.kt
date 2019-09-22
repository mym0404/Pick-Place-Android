package korea.seoul.pickple.ui.course.intro.review


import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.appcompat.widget.ListPopupWindow
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.ListPopupWindowCompat
import androidx.core.widget.PopupWindowCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import korea.seoul.pickple.R
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.databinding.FragmentReviewBinding
import korea.seoul.pickple.ui.BaseFragment
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import korea.seoul.pickple.ui.course.intro.all_review.ShowAllReviewActivity
import korea.seoul.pickple.ui.navigation.NavigationArgs
import korea.seoul.pickple.ui.navigation.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {
    private lateinit var mReviewAdapter: ReviewAdapter
    private val mCourseIntroViewModel: CourseIntroViewModel by sharedViewModel()
    private var mIsCourseReview: Boolean = false
    private var mIsFullReview: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.run {
            mIsCourseReview = getBoolean(ARG_IS_COURSE_REVIEW, false)
            mIsFullReview = getBoolean(ARG_IS_FULL_REVIEW, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.run {
            mReviewAdapter = ReviewAdapter(this, listOf())
            mBinding.apply {
                courseIntroViewModel = mCourseIntroViewModel
                isCourseReview = mIsCourseReview
                isFullReview = mIsFullReview
                rvReviewList.apply {
                    adapter = mReviewAdapter
                    layoutManager = LinearLayoutManager(this@run)
                }
                btnSelectEmotion.setOnClickListener {
                    // TODO Emotion Select 팝업이 나와야함
                    val popupWindow = PopupWindow(layoutInflater.inflate(R.layout.popup_review_emotion, null),
                        ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT, true)

                    popupWindow.run {
                        contentView.apply {
                            findViewById<ImageView>(R.id.btnEmotion1).setEmotionButtonAndDismiss(Review.Emotion.EMOTION1, popupWindow)
                            findViewById<ImageView>(R.id.btnEmotion2).setEmotionButtonAndDismiss(Review.Emotion.EMOTION2, popupWindow)
                            findViewById<ImageView>(R.id.btnEmotion3).setEmotionButtonAndDismiss(Review.Emotion.EMOTION3, popupWindow)
                            findViewById<ImageView>(R.id.btnEmotion4).setEmotionButtonAndDismiss(Review.Emotion.EMOTION4, popupWindow)
                            findViewById<ImageView>(R.id.btnEmotion5).setEmotionButtonAndDismiss(Review.Emotion.EMOTION5, popupWindow)
                        }
                        showAsDropDown(it)
                    }
                }
                btnShowAllReview.setOnClickListener {
//                    navigate(this@run, NavigationArgs.ShowAllReviewArg(mIsCourseReview))
                    supportFragmentManager.beginTransaction().apply {
                        addToBackStack(null)
                        setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        replace(android.R.id.content, ShowAllReviewActivity.newInstance(mIsCourseReview))
                    }.commit()
                }
            }
        }
    }

    private fun ImageView.setEmotionButtonAndDismiss(emotion: Review.Emotion, popup: PopupWindow) {
        apply {
            setImageResource(emotion.toDrawableResourceId())
            setOnClickListener {
                mCourseIntroViewModel.selectEmotion(emotion)
                popup.dismiss()
            }
        }
    }

    companion object {
        const val ARG_IS_COURSE_REVIEW = "isCourseReview"
        const val ARG_IS_FULL_REVIEW = "isFullReview"

        @JvmStatic
        fun newInstance(isCourseReview: Boolean, isFullReview: Boolean = false): ReviewFragment {
            return ReviewFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_IS_COURSE_REVIEW, isCourseReview)
                    putBoolean(ARG_IS_FULL_REVIEW, isFullReview)
                }
            }
        }
    }
}
