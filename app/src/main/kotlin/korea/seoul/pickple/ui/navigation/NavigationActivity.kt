package korea.seoul.pickple.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.databinding.ActivityNavigationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityNavigationBinding

    private val mViewModel : NavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNavigationBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        initRecyclerViews()
        observeViewModel()
    }

    private fun initRecyclerViews() {
        mBinding.recyclerViewSeoulNews.apply {
            adapter = SeoulNewsAdapter(mViewModel)
        }
        mBinding.recyclerViewMyCourse.apply {
            adapter = NavigationCourseAdapter(mViewModel)
        }
        mBinding.recyclerViewPickPlace.apply {
            adapter = NavigationCourseAdapter(mViewModel)
        }
    }

    private fun observeViewModel() {
        mViewModel.apply {

        }
    }
}