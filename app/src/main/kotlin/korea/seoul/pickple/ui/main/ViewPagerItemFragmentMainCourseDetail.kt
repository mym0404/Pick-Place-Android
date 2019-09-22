package korea.seoul.pickple.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.viewpager_item_main_course_detail.*

class ViewPagerItemFragmentMainCourseDetail: Fragment() {

    private lateinit var adapter: RecyclerAdapterMainCourseDetail // 리사이클러뷰 어댑터


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.viewpager_item_main_course_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

    // 리사이클러뷰 설정
    private fun setRecyclerView() {
        var list = listOf("12", "@3", "33")

//        adapter = RecyclerAdapterMainCourseDetail(, list)


    }

    // 탭 선택시 리스너 설정
    private fun setTabListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

        })
    }
}