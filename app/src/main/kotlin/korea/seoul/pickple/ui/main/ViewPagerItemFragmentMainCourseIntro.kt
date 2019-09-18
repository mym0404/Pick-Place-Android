package korea.seoul.pickple.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import korea.seoul.pickple.R

class ViewPagerItemFragmentMainCourseIntro: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.viewpager_item_main_course_intro, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}