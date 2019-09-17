package korea.seoul.pickple.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.fragment_main_course_detail.*

class MainCourseDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_course_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = FragmentAdapterMainCourseDetail(this)
        frag_main_course_detail_viewpager2_horizontal.adapter = adapter

        setViewPagerOnPageChangeListener()
    }

    override fun onResume() {
        super.onResume()

        frag_main_course_detail_viewpager2_horizontal.setCurrentItem((activity as MainActivity).currentPosition, false)
    }

    private fun setViewPagerOnPageChangeListener() {
        frag_main_course_detail_viewpager2_horizontal.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                (activity as MainActivity).currentPosition = position
            }
        })
    }
}