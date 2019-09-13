package korea.seoul.pickple.ui.course.place_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar.*
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityPlaceDetailBinding
import korea.seoul.pickple.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * 코스소개 - 첫화면 을 구현한 화면이다.
 * 복잡하지 않은 화면이라 ViewModel 을 생략하고, Course 정보만 단순히 그려주고 마무리하자.
 *
 * (반드시 넘겨야한다.) placesIds : 우리가 선택한 코스의 장소들 정보
 * @author greedy0110
 * */
class PlaceDetailActivity : BaseActivity<ActivityPlaceDetailBinding>(R.layout.activity_place_detail) {
    private val mViewModel: PlaceDetailViewModel by viewModel { parametersOf(listOf<Int>()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.vp2PlaceDetail.apply {
            adapter = PlaceDetailViewPagerAdapter(this@PlaceDetailActivity, listOf())
            registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        mViewModel.selectPlace(position + 1)
                    }
                }
            )
        }

        mViewModel.placeIds = listOf(1,2,3,4)
        mBinding.viewModel = mViewModel


        makeAppBar()
    }

    private fun makeAppBar() {
        // 우리의 toolbar를 앱바로 지정한다
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.run {
            // DISPLAY_HOME_AS_UP을 세팅해서 뒤로가기 버튼을 보여준다.
            setDisplayOptions(
                DISPLAY_HOME_AS_UP
                , DISPLAY_HOME_AS_UP or DISPLAY_SHOW_TITLE) // 타이틀이 보이지 않아야한다.
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.actionLike -> {
            // TODO 장소 좋아요 서버 통신이 있어야한다 / 좋아요 갯수 + 1
            // TODO 장소 좋아요 여부에 따라서 그려주는 아이콘이 달라저야 한다.
            Log.d("pick-ple", "장소 좋아요")
            true
        }
        R.id.actionShowAll -> {
            // TODO 모든 코스 보기 화면으로 넘어가야한다.
            Log.d("pick-ple", "모든 코스 보기")
            true
        }
        android.R.id.home -> {
            // 뒤로가기 버튼을 눌렀을 때
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
