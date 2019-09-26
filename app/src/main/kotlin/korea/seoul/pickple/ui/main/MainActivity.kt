package korea.seoul.pickple.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.navigate
import korea.seoul.pickple.ui.navigation.NavigationActivity
import korea.seoul.pickple.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main_course.*

class MainActivity : AppCompatActivity() {

    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initVerticalViewPager()
        setVerticalViewPagerPageChangeListener()
        setToolbarClickListener()
    }

    private fun initVerticalViewPager() {
        act_main_viewpager2_vertical.apply { // 이렇게 하면 코틀린 좀 쓰는 놈인가 하고 반한다 사람들이 ,,
            orientation = ViewPager2.ORIENTATION_VERTICAL // 뷰페이저를 수직으로 설정
            adapter = FragmentAdatperMainVertical(this@MainActivity) // 어댑터 설정
            offscreenPageLimit = 2
        }
    }

    private fun setToolbarClickListener() {
        toolbar_main_course_ibtn_search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)

            startActivity(intent)
        }

        toolbar_main_course_ibtn_nav.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)

            startActivity(intent)
        }
    }

    private fun setVerticalViewPagerPageChangeListener() {
        act_main_viewpager2_vertical.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCourseName()
                when (position) {
                    0 -> {
                        toolbar_main_course_tv_course_name.visibility = View.GONE
                        toolbar_main_course_indicator_view.visibility = View.GONE

                        toolbar_main_img_logo.visibility = View.VISIBLE
                    }
                    1-> {
                        toolbar_main_course_tv_course_name.visibility = View.VISIBLE
                        toolbar_main_course_indicator_view.visibility = View.VISIBLE

                        toolbar_main_img_logo.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setCourseName() {
        when (currentPosition) {
            0 -> {
                toolbar_main_course_tv_course_name.text = "오래 가게 코스"
            }
            1 -> {
                toolbar_main_course_tv_course_name.text = "한국 전통 코스"
            }
            2 -> {
                toolbar_main_course_tv_course_name.text = "사용자 코스"
            }
        }
    }

    override fun onBackPressed() {

        if(act_main_viewpager2_vertical.currentItem == 1) {
            act_main_viewpager2_vertical.currentItem = 0
        }else {

            super.onBackPressed()
        }
    }

    fun onClickIntroPage(position : Int) {
        navigate(this,NavigationArgs.NavigationCourseArg(Course.Type.parse(position)))
    }
}