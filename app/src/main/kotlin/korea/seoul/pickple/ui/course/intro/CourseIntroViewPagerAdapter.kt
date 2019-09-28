package korea.seoul.pickple.ui.course.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import korea.seoul.pickple.ui.course.intro.place_detail.PlaceDetailActivity
import korea.seoul.pickple.ui.course.intro.simple_intro.CourseSimpleIntroActivity
import korea.seoul.pickple.ui.course.intro.unite_intro.UniteCourseActivity
import java.lang.IllegalArgumentException

class CourseIntroViewPagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> CourseSimpleIntroActivity()
            1 -> UniteCourseActivity()
            2 -> PlaceDetailActivity()
            else -> throw IllegalArgumentException("course intro fragment indexString error")
        }
    }

    override fun getCount(): Int {
        return 3
    }
}