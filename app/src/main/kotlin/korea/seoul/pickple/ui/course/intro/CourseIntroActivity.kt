package korea.seoul.pickple.ui.course.intro

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.viewpager.widget.ViewPager
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityCourseIntroBinding
import korea.seoul.pickple.ui.BaseActivity
import korea.seoul.pickple.ui.navigation.NavigationArgs
import korea.seoul.pickple.ui.navigation.navigate
import korea.seoul.pickple.ui.navigation.parseIntent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CourseIntroActivity : BaseActivity<ActivityCourseIntroBinding>(R.layout.activity_course_intro) {
    private val viewModel: CourseIntroViewModel by viewModel { parametersOf(0) }
    private var toolbarActionShare: MenuItem? = null
    private val REQUEST_SHOW_ALL_COURSES = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // navigation 으로 들어온 것 데이터 파싱하자.
        val args = parseIntent(intent)
        val courseId = args.courseId

        mBinding.apply {
            vpCourseIntro.adapter = CourseIntroViewPagerAdapter(
                supportFragmentManager
            )
            vpCourseIntro.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
                override fun onPageSelected(position: Int) {
                    when(position) {
                        0 -> {
                            // appbar 끔
                            supportActionBar?.hide()
                        }
                        1 -> {
                            // course에 관련된 appbar
                            supportActionBar?.show()
                            toolbarActionShare?.isVisible = true
                        }
                        2 -> {
                            // place 관련 appbar 그러면서도, place 아이디마다 달라질 수 있음
                            supportActionBar?.show()
                            toolbarActionShare?.isVisible = false
                        }
                    }
                }
            })

            setSupportActionBar(toolbarCourseIntro)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                // 첫 화면에서 abb bar는 가린 상태!
                hide()
            }
            // shadow를 없에기 위해서 추가
            appbarLayoutCourseIntro.outlineProvider = null
        }

        viewModel.courseId = courseId
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_unite_course_toolbar, menu)
        toolbarActionShare = menu?.findItem(R.id.actionShare)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                // 액티비티를 종료하자.
                Log.d("seungmin", "네비게이션 버튼")
                finish()
                true
            }
            R.id.actionShare -> {
                // TODO 코스 공유하기 버튼임!
                Log.d("seungmin", "${viewModel.courseId} 공유버튼")
                true
            }
            R.id.actionShowAll -> {
                // 전체 코스 보기 화면으로 넘어가야함, 만약 거기서 코스를 선택했다면?! course Id를 변경해주자! (응답을 받아오자.)
                Log.d("seungmin", "전체 코스보기")
                navigate(this, NavigationArgs.ShowAllCourseArg(), REQUEST_SHOW_ALL_COURSES)
                true
            }
            else -> onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQUEST_SHOW_ALL_COURSES -> {
                if (resultCode == Activity.RESULT_OK) {
                    // 다른 course를 선택했다. 코스를 변경하자.
                    viewModel.courseId = data?.getIntExtra(COURSE_ID, 0)?:0
                    // 코스를 변경하고, 맨 처음 화면부터 시작하자.
                    mBinding.vpCourseIntro.setCurrentItem(0, false)
                }
                else {
                    // 코스를 선택하지 않았다. 그냥 아무것도 변경하지 말자!
                }
            }
        }
    }

    companion object {
        const val COURSE_ID = "COURSE_ID"
    }
}
