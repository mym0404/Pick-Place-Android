package korea.seoul.pickple.ui.course.intro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityCourseIntroBinding
import korea.seoul.pickple.ui.BaseActivity
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.navigate
import korea.seoul.pickple.ui.parseIntent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CourseIntroActivity : BaseActivity<ActivityCourseIntroBinding>(R.layout.activity_course_intro) {
    private val viewModel: CourseIntroViewModel by viewModel { parametersOf(0) }
    private var toolbarActionShare: MenuItem? = null

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
//        viewModel.courseId = 1 // 테스트 용으로 서버에 더미데이터가 들어있는 1로 변경하였다.
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
                // 코스 공유하기 버튼임! 현재 버전에선 단순히 앱을 킬 수 있는 다이나믹 링크를 제공하는 것으로 결정!
                shareCourse()
                Log.d("seungmin", "${viewModel.courseId} 공유버튼")
                true
            }
            R.id.actionShowAll -> {
                // 코스의 전체 장소 보기 화면으로 넘어가야함, 만약 거기서 장소를 선택했다면?! 선택된 index를 전달해주자!
                Log.d("seungmin", "전체 코스보기")
                viewModel.places.value?.let { places ->
                    navigate(this, NavigationArgs.ShowAllCourseArg(ArrayList(places)), REQUEST_SHOW_ALL_COURSES)
                }
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
                    Log.d("seungmin", "course intro on activity result $data")
                    val index = data?.getIntExtra(SET_CURRENT_INDEX, 1)?:1
                    // 다른 place를 선택했다. 코스를 변경하자.
                    viewModel.selectPlace(index)
                    // 장소 보는 화면으로 넘어가자
                    mBinding.vpCourseIntro.setCurrentItem(2, false)
                }
                else {
                    // 장소를 선택하지 않았다. 그냥 아무것도 변경하지 말자!
                }
            }
        }
    }

    private fun shareCourse() {
        // 다이나믹 링크 URL을 공유한다.
        startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "https://pickple.page.link/jTpt")
        }, "함께 가고싶은 플레이스를 추천하세요!"))
    }

    companion object {
        const val REQUEST_SHOW_ALL_COURSES = 1234
        const val SET_CURRENT_INDEX = "SET_CURRENT_INDEX"
    }
}
