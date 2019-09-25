package korea.seoul.pickple.ui.course.intro.unite_intro

import android.os.Bundle
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityUniteCourseBinding
import korea.seoul.pickple.ui.BaseFragment
import korea.seoul.pickple.ui.course.intro.CourseIntroViewModel
import korea.seoul.pickple.ui.course.intro.review.ReviewFragment
import korea.seoul.pickple.ui.NavigationArgs
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
                // TODO Map 을 화면에 띄워주자. (course를 함께 건내야 한다.)
                navigate(
                    curActivity = this,
                    arg = NavigationArgs.MapActivityArg(mCourseIntroViewModel.course.value)
                )
            }
        }

        //makeAppBar()
    }

//    private fun makeAppBar() {
//        // 우리의 toolbar를 앱바로 지정한다
//        setSupportActionBar(mBinding.toolbar)
//        supportActionBar?.run {
//            // DISPLAY_HOME_AS_UP을 세팅해서 뒤로가기 버튼을 보여준다.
//            setDisplayOptions(
//                ActionBar.DISPLAY_HOME_AS_UP
//                , ActionBar.DISPLAY_HOME_AS_UP or ActionBar.DISPLAY_SHOW_TITLE
//            ) // 타이틀이 보이지 않아야한다.
//        }
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
//        R.id.actionShare -> {
//            // TODO course 공유하기 팝업 띄워주기
//            true
//        }
//        R.id.actionLike -> {
//            // TODO course 좋아요 표시하기
//            true
//        }
//        R.id.actionShowAll -> {
//            // TODO 모든 코스 보기 화면 띄우기
//            true
//        }
//        android.R.id.home -> {
//            // 네비게이션 버튼을 눌렀을 때
//            finish()
//            true
//        }
//        else -> onOptionsItemSelected(item)
//    }
}
