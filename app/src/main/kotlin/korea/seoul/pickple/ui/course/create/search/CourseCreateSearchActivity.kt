package korea.seoul.pickple.ui.course.create.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.common.widget.observeOnce
import korea.seoul.pickple.databinding.ActivityCourseCreateSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseCreateSearchActivity : AppCompatActivity() {

    companion object {
        const val COURSE_SEARCH_REQUEST_CODE = 333
        const val COURSE_SEARCH_NONE_RESULT_CODE = 444
        const val COURSE_SEARCH_WITH_RESULT_CODE = 555
        const val EXTRA_SELECTED_PLACE_CODE = "EXTRA_SELECTED_PLACE_CODE"
    }

    private lateinit var mBinding : ActivityCourseCreateSearchBinding

    private val mViewModel : CourseCreateSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCourseCreateSearchBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            adapter = CourseCreateSearchAdapter(mViewModel,this@CourseCreateSearchActivity)

        }
    }

    private fun observeViewModel() {
        mViewModel.apply {

            clickBack.observeOnce(this@CourseCreateSearchActivity) {
                setResult(COURSE_SEARCH_NONE_RESULT_CODE,null)
                onBackPressed()
            }

            clickAdd.observeOnce(this@CourseCreateSearchActivity) {place->
                setResult(COURSE_SEARCH_WITH_RESULT_CODE, Intent().apply{putExtra(EXTRA_SELECTED_PLACE_CODE,place)})
                finish()
            }

        }
    }


    override fun onBackPressed() {
        setResult(COURSE_SEARCH_NONE_RESULT_CODE)
        super.onBackPressed()
    }
}