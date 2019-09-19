package korea.seoul.pickple.ui.course.intro.all_course

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityShowAllCoursesBinding
import korea.seoul.pickple.ui.BaseActivity
import korea.seoul.pickple.ui.course.intro.CourseIntroActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowAllCoursesActivity : BaseActivity<ActivityShowAllCoursesBinding>(R.layout.activity_show_all_courses) {
    private val allCoursesViewModel: ShowAllCoursesViewModel by viewModel()
    private lateinit var allCourseAdapter: ShowAllCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        allCourseAdapter = ShowAllCourseAdapter(this, listOf(), allCoursesViewModel)
        mBinding.apply {
            btnShowAllCoursesClose.setOnClickListener {
                // 아무 course도 선택하지 않고 종료하는 것!
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
            rvShowAllCourses.apply {
                adapter = allCourseAdapter
                layoutManager = GridLayoutManager(this@ShowAllCoursesActivity, 2)
            }

            // 사용자가 course를 누르면 응답을 담아서 화면을 종료하자.
            allCoursesViewModel.clickedCourseId.observe(this@ShowAllCoursesActivity, Observer {
                intent.putExtra(CourseIntroActivity.COURSE_ID, it)
                setResult(Activity.RESULT_OK)
                finish()
            })

            viewModel = allCoursesViewModel
        }
    }
}
