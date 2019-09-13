package korea.seoul.pickple.ui.course.create

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.databinding.ActivityCourseCreateSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseCreateSearchActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityCourseCreateSearchBinding

    private val mViewModel : CourseCreateSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCourseCreateSearchBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        observeViewModel()
    }

    private fun observeViewModel() {
        mViewModel.apply {

        }
    }
}