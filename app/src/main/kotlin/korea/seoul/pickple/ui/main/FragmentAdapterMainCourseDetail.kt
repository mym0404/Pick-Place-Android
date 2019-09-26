package korea.seoul.pickple.ui.main

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapterMainCourseDetail(fragment : Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPagerItemFragmentMainCourseDetail()

        fragment.currentPosition = position

        return fragment
    }
}