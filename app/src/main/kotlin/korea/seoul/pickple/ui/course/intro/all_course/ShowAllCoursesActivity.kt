package korea.seoul.pickple.ui.course.intro.all_course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityShowAllCoursesBinding
import korea.seoul.pickple.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowAllCoursesActivity : BaseActivity<ActivityShowAllCoursesBinding>(R.layout.activity_show_all_courses) {
    private val allCoursesViewModel: ShowAllCoursesViewModel by viewModel()
    private lateinit var allCourseAdapter: ShowAllCourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        allCourseAdapter = ShowAllCourseAdapter(this, listOf())
        mBinding.apply {
            btnShowAllCoursesClose.setOnClickListener {
                // activity 종료해서 끝내자.
                finish()
            }
            rvShowAllCourses.apply {
                adapter = allCourseAdapter
                layoutManager = GridLayoutManager(this@ShowAllCoursesActivity, 2)
            }
            viewModel = allCoursesViewModel
        }
    }
}
