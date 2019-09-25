package korea.seoul.pickple.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.viewpager_item_main_course_intro.view.*

class ViewPagerItemFragmentMainCourseIntro(private val resId : Int): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.viewpager_item_main_course_intro, container, false)
        view.viewpager_item_img_course.setImageResource(resId)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}