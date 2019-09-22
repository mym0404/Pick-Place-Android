package korea.seoul.pickple.ui.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.common.util.LinearItemDecoration
import korea.seoul.pickple.common.widget.observeOnce
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
            addItemDecoration(LinearItemDecoration(this@NavigationActivity,16))
        }
        mBinding.recyclerViewMyCourse.apply {
            adapter = NavigationCourseAdapter(mViewModel)
            addItemDecoration(LinearItemDecoration(this@NavigationActivity,16))
        }
        mBinding.recyclerViewPickPlace.apply {
            adapter = NavigationCourseAdapter(mViewModel)
            addItemDecoration(LinearItemDecoration(this@NavigationActivity,16))
        }
    }

    private fun observeViewModel() {
        mViewModel.apply {
            clickSeoulNews.observeOnce(this@NavigationActivity) {
                Intent(Intent.ACTION_VIEW).apply {
                    setData(Uri.parse(it.url))
                    startActivity(this)
                }
            }
        }
    }
}