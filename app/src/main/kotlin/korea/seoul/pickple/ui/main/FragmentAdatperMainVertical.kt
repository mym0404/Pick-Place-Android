package korea.seoul.pickple.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdatperMainVertical(activity : FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            val introFragment = MainCourseIntroFragment()

            return introFragment
        }
        else {
            val detailFragment = MainCourseDetailFragment()

            return detailFragment
        }
    }
}