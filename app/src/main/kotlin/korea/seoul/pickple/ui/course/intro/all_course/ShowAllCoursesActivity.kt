package korea.seoul.pickple.ui.course.intro.all_course

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityShowAllCoursesBinding
import korea.seoul.pickple.ui.BaseActivity
import korea.seoul.pickple.ui.course.intro.CourseIntroActivity
import korea.seoul.pickple.ui.parseIntent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ShowAllCoursesActivity : BaseActivity<ActivityShowAllCoursesBinding>(R.layout.activity_show_all_courses) {
    private val allCoursesViewModel: ShowAllCoursesViewModel by viewModel { parametersOf(parseIntent(intent).places.toList()) }
    private lateinit var allCourseAdapter: ShowAllCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        allCourseAdapter = ShowAllCourseAdapter(this, listOf(), allCoursesViewModel)
        mBinding.apply {
            btnShowAllCoursesClose.setOnClickListener {
                // 아무 index도 선택하지 않고 종료하는 것!
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
            rvShowAllCourses.apply {
                adapter = allCourseAdapter
                layoutManager = GridLayoutManager(this@ShowAllCoursesActivity, 2)
            }

            // 사용자가 index를 누르면 응답을 담아서 화면을 종료하자.
            allCoursesViewModel.clickedIndex.observe(this@ShowAllCoursesActivity, Observer {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(CourseIntroActivity.SET_CURRENT_INDEX, it)
                })
                finish()
            })

            viewModel = allCoursesViewModel
        }
    }
}
