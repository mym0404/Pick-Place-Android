package korea.seoul.pickple.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.R
import korea.seoul.pickple.data.api.MainAPI
import kotlinx.android.synthetic.main.fragment_main_course_detail.*
import kotlinx.android.synthetic.main.toolbar_main_course.*
import org.koin.android.ext.android.inject

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
        setDetailFragInfo((activity as MainActivity).currentPosition)
    }

    private fun setViewPagerOnPageChangeListener() {
        frag_main_course_detail_viewpager2_horizontal.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                (activity as MainActivity).currentPosition = position

                setDetailFragInfo(position)
            }
        })
    }

    private fun setDetailFragInfo(position : Int) {
        activity?.toolbar_main_course_indicator_view?.selection = position

        when (position) {
            0 -> {
                activity?.toolbar_main_course_tv_course_name?.text = "오래 가게 코스"
            }
            1 -> {
                activity?.toolbar_main_course_tv_course_name?.text = "한국 전통 코스"
            }
            2 -> {
                activity?.toolbar_main_course_tv_course_name?.text = "사용자 코스"
            }
        }
    }
}