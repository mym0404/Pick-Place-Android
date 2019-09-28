package korea.seoul.pickple.ui.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.common.util.LinearItemDecoration
import korea.seoul.pickple.common.widget.observeOnce
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.databinding.ActivityNavigationBinding
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.navigate
import korea.seoul.pickple.ui.navigation.pickplace.NavigationPickPlaceActivity
import korea.seoul.pickple.ui.navigation.review.NavigationReviewActivity
import korea.seoul.pickple.ui.setting.SettingActivity
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

        initViews()
        initRecyclerViews()
        observeViewModel()
    }

    private fun initViews() {
        mBinding.actNavIbtnClose.setOnClickListener {
            onBackPressed()
        }
        mBinding.actNavRelativeIbtnSetting.setOnClickListener {
            Intent(this@NavigationActivity,SettingActivity::class.java).apply {
                startActivity(this)
            }
        }
        mBinding.imageview8.setOnClickListener {
            Intent(this@NavigationActivity,NavigationReviewActivity::class.java).apply {
                startActivity(this)
            }
        }

        mBinding.imageview4.setOnClickListener {

            navigate(this,NavigationArgs.NavigationCourseArg(Course.Type.CUSTOM))

        }
        mBinding.imageview6.setOnClickListener {
            Intent(this@NavigationActivity,NavigationPickPlaceActivity::class.java).apply {
                startActivity(this)
            }
        }
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
            adapter = NavigationPickPlaceAdapter(mViewModel)
            addItemDecoration(LinearItemDecoration(this@NavigationActivity,16))
        }
        mBinding.recyclerViewReview.apply {
            adapter = NavigationReviewAdapter(mViewModel)
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
            clickCourse.observeOnce(this@NavigationActivity) {course->
                navigate(this@NavigationActivity,NavigationArgs.CourseCreateArgs(
                    course.cName,Uri.parse(course.cThumbnail),"",listOf(),true,course.courseIdx
                ))
            }
            clickPlace.observeOnce(this@NavigationActivity) {
            }
            clickReview.observeOnce(this@NavigationActivity) {

            }
        }
    }
}