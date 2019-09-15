package korea.seoul.pickple.ui.course.create.intro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.common.extensions.toast
import korea.seoul.pickple.common.util.GalleryUtil
import korea.seoul.pickple.common.widget.observeOnce
import korea.seoul.pickple.databinding.ActivityCourseCreateIntroBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseCreateIntroActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityCourseCreateIntroBinding

    private val mViewModel : CourseCreateIntroViewModel by viewModel()

    private val mGalleryUtil : GalleryUtil by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCourseCreateIntroBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.lifecycleOwner = this
        mBinding.vm = mViewModel

        observeViewModel()
    }

    private fun observeViewModel() {
        mViewModel.apply {
            clickBackButton.observeOnce(this@CourseCreateIntroActivity) {
                onBackPressed()
            }

            clickImageAdd.observeOnce(this@CourseCreateIntroActivity) {
                mGalleryUtil.choosePhotoFromGallery(this@CourseCreateIntroActivity)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mGalleryUtil.onActivityResult(requestCode,resultCode,data,object : GalleryUtil.GalleryUtilListener {
            override fun onReadyImageUri(uri: Uri) {
                mViewModel.onImageUriChanged(uri)
            }


            override fun onFailLoad() {
                toast("갤러리에서 사진을 불러오는 데 실패했습니다.")
            }
        })
    }
}