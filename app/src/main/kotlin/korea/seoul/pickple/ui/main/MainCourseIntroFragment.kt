package korea.seoul.pickple.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import korea.seoul.pickple.ui.course.create.intro.CourseCreateIntroActivity
import kotlinx.android.synthetic.main.fragment_main_course_detail.*
import kotlinx.android.synthetic.main.fragment_main_course_intro.*
import kotlinx.android.synthetic.main.toolbar_main_course.*

class MainCourseIntroFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_course_intro, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()

        frag_main_course_intro_viewpager2_horizontal.setCurrentItem((activity as MainActivity).currentPosition, false)
        setFragInfo((activity as MainActivity).currentPosition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 값 바꿔주는 곳 ,,?
        val adapter = FragmentAdapterMainCourseIntro(this)
        frag_main_course_intro_viewpager2_horizontal.adapter = adapter

        setOnPageChangeListener()
        setFloatingButtonListener()
    }

    private fun setFloatingButtonListener() {
        viewpager_item_floating_button_make_course.setOnClickListener {
            val intent = Intent(activity, CourseCreateIntroActivity::class.java)

            startActivity(intent)
        }
    }

    private fun setOnPageChangeListener() {
        frag_main_course_intro_viewpager2_horizontal.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                (activity as MainActivity).currentPosition = position

                setFragInfo(position)
            }
        })
    }

    private fun setFragInfo(position: Int) {
        viewpager_item_page_indicator_view.selection = position
        activity?.toolbar_main_course_indicator_view?.selection = position

        when (position) {
            0 -> {
                viewpager_item_tv_course_name.text = "오래 가게 코스"
                viewpager_item_tv_course_detail.text = "그 때 그 시절 추억이 담긴 코스"
                viewpager_item_floating_button_make_course.visibility = View.GONE
            }
            1 -> {
                viewpager_item_tv_course_name.text = "한국 전통 코스"
                viewpager_item_tv_course_detail.text = "서울의 궁궐을 알아보는 코스"
                viewpager_item_floating_button_make_course.visibility = View.GONE
            }
            2 -> {
                viewpager_item_tv_course_name.text = "사용자 코스"
                viewpager_item_tv_course_detail.text = "사용자가 직접 개발하는 코스"
                viewpager_item_floating_button_make_course.visibility = View.VISIBLE
            }
        }
    }
}