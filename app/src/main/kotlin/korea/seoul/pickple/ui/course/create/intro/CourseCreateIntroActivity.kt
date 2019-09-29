package korea.seoul.pickple.ui.course.create.intro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.common.extensions.showSnackBar
import korea.seoul.pickple.common.extensions.toast
import korea.seoul.pickple.common.util.GalleryUtil
import korea.seoul.pickple.common.util.PermissionDexterUtil
import korea.seoul.pickple.common.util.PermissionListener
import korea.seoul.pickple.common.widget.observeOnce
import korea.seoul.pickple.databinding.ActivityCourseCreateIntroBinding
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.navigate
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseCreateIntroActivity : AppCompatActivity() {

    private val TAG = CourseCreateIntroActivity::class.java.simpleName

    private lateinit var mBinding : ActivityCourseCreateIntroBinding

    private val mViewModel : CourseCreateIntroViewModel by viewModel()

    private val mGalleryUtil : GalleryUtil by inject()

    private val dexterUtil : PermissionDexterUtil by inject()

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
                dexterUtil.requestPermissions(this@CourseCreateIntroActivity,object : PermissionListener {
                    override fun onPermissionGranted() {
                        mGalleryUtil.choosePhotoFromGallery(this@CourseCreateIntroActivity)
                    }

                    override fun onPermissionShouldBeGranted(deniedPermissions: List<String>) {
                        mBinding.root.showSnackBar("저장소 접근 권한을 허용해주세요.")
                    }

                    override fun onAnyPermissionsPermanentlyDeined(deniedPermissions: List<String>, permanentDeniedPermissions: List<String>) {
                        mBinding.root.showSnackBar("저장소 접근 권한이 영구적으로 거부되었습니다. \n[설정] - [어플리케이션] 에서 해제하셔야 사용자 코스 생성 만들기가 가능합니다.")
                    }
                },mutableListOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE))

            }

            clickPlaceAdd.observeOnce(this@CourseCreateIntroActivity) {(pair1, pair2) ->
                val title = pair1.first
                val thumbnail = pair1.second
                val description = pair2.first
                val tagString = pair2.second

                val tagList = tagString.filterNot { it == ',' || it == ' ' }.split('#')

                navigate(this@CourseCreateIntroActivity, NavigationArgs.CourseCreateArgs(title, thumbnail, description, tagList,false))
            }

            toastMsg.observeOnce(this@CourseCreateIntroActivity) {
                toast(it)
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