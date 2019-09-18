package korea.seoul.pickple.ui.course.intro.place_detail

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityPlaceDetailBinding
import korea.seoul.pickple.ui.BaseFragment
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import korea.seoul.pickple.ui.course.intro.review.ReviewFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

/**
 * 코스소개 - 첫화면 을 구현한 화면이다.
 *
 * @author greedy0110
 * */
class PlaceDetailActivity : BaseFragment<ActivityPlaceDetailBinding>(R.layout.activity_place_detail) {
    private val mCourseIntroViewModel: CourseIntroViewModel by sharedViewModel { parametersOf(0) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run {
            supportFragmentManager.beginTransaction().apply {
                disallowAddToBackStack()
                replace(R.id.placePlaceDetailReviewFragment
                    , ReviewFragment.newInstance(false), // Place의 리뷰이다.
                    null)
            }.commit()

            mBinding.vp2PlaceDetail.apply {
                adapter = PlaceDetailViewPagerAdapter(this@run, listOf())
                registerOnPageChangeCallback(
                    object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            mCourseIntroViewModel.selectPlace(position + 1)
                        }
                    }
                )
            }

            mBinding.viewModel = mCourseIntroViewModel


//            makeAppBar()
        }
    }

//    private fun makeAppBar() {
//        // 우리의 toolbar를 앱바로 지정한다
//        setSupportActionBar(mBinding.toolbar)
//        supportActionBar?.run {
//            // DISPLAY_HOME_AS_UP을 세팅해서 뒤로가기 버튼을 보여준다.
//            setDisplayOptions(
//                DISPLAY_HOME_AS_UP
//                , DISPLAY_HOME_AS_UP or DISPLAY_SHOW_TITLE) // 타이틀이 보이지 않아야한다.
//        }
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
//        R.id.actionLike -> {
//            // TODO 장소 좋아요 서버 통신이 있어야한다 / 좋아요 갯수 + 1
//            // TODO 장소 좋아요 여부에 따라서 그려주는 아이콘이 달라저야 한다.
//            Log.d("pick-ple", "장소 좋아요")
//            true
//        }
//        R.id.actionShowAll -> {
//            // TODO 모든 코스 보기 화면으로 넘어가야한다.
//            Log.d("pick-ple", "모든 코스 보기")
//            true
//        }
//        android.R.id.home -> {
//            // 뒤로가기 버튼을 눌렀을 때
//            finish()
//            true
//        }
//        else -> super.onOptionsItemSelected(item)
//    }
}
