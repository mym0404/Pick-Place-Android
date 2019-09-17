package korea.seoul.pickple.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVerticalViewPager()
    }

    private fun initVerticalViewPager() {
        act_main_viewpager2_vertical.apply { // 이렇게 하면 코틀린 좀 쓰는 놈인가 하고 반한다 사람들이 ,,
            orientation = ViewPager2.ORIENTATION_VERTICAL // 뷰페이저를 수직으로 설정
            adapter = FragmentAdatperMainVertical(this@MainActivity) // 어댑터 설정
        }
    }
}