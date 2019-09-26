package korea.seoul.pickple.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import korea.seoul.pickple.R

class FragmentAdapterMainCourseIntro(fragment : Fragment) : FragmentStateAdapter(fragment) {



    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        val fragment = ViewPagerItemFragmentMainCourseIntro(
            when(position) {
                0-> R.drawable.main_illustration_01
                1-> R.drawable.main_illustration_02
                2-> R.drawable.main_illustration_03
                else->R.drawable.main_illustration_01
            },position
        )

        return fragment
    }
}