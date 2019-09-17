package korea.seoul.pickple.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapterMainCourseIntro(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        val fragment = ViewPagerItemFragmentMainCourseIntro()

        return fragment
    }
}