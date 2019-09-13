package korea.seoul.pickple.ui.course.unite_intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityPlaceDetailBinding
import korea.seoul.pickple.databinding.ActivityUniteCourseBinding
import korea.seoul.pickple.ui.course.map.MapActivity
import korea.seoul.pickple.view.PickpleMapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * 코스소개 - 묶음소개 를 구현한 화면이다.
 *
 * (반드시 넘겨야한다.) courseId : 우리가 선택한 코스의 아이디 정보
 * @author greedy0110
 * */
class UniteCourseActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityUniteCourseBinding
    private val mViewModel: UniteCourseViewModel by viewModel { parametersOf(0) }
    private var courseId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUniteCourseBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        courseId = intent.getIntExtra("courseId", -1)
        courseId = 1 // test 용!

        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel.apply {
            courseId = this@UniteCourseActivity.courseId
        }

        mBinding.btnUniteCourseMap.setOnClickListener {
            // TODO Map 을 화면에 띄워주자. (courseId를 함께 건내야 한다.)
            startActivity(Intent(this, MapActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.actionShare -> {
            // TODO course 공유하기 팝업 띄워주기
            true
        }
        R.id.actionLike -> {
            // TODO course 좋아요 표시하기
            true
        }
        R.id.actionShowAll -> {
            // TODO 모든 코스 보기 화면 띄우기
            true
        }
        android.R.id.home -> {
            // 네비게이션 버튼을 눌렀을 때
            finish()
            true
        }
        else -> onOptionsItemSelected(item)
    }
}
