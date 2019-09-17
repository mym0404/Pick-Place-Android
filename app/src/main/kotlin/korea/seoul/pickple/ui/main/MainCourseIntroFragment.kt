package korea.seoul.pickple.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.fragment_main_course_detail.*
import kotlinx.android.synthetic.main.fragment_main_course_intro.*

class MainCourseIntroFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_course_intro, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()

        frag_main_course_intro_viewpager2_horizontal.setCurrentItem((activity as MainActivity).currentPosition, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 값 바꿔주는 곳 ,,?
        val adapter = FragmentAdapterMainCourseIntro(this)
        frag_main_course_intro_viewpager2_horizontal.adapter = adapter

        frag_main_course_intro_viewpager2_horizontal.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                (activity as MainActivity).currentPosition = position
            }
        })
    }
}