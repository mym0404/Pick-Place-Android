package korea.seoul.pickple.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.toast
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.api.MainAPI
import korea.seoul.pickple.data.api.response.main.MainListResponse
import korea.seoul.pickple.data.entity.Course
import kotlinx.android.synthetic.main.viewpager_item_main_course_detail.*
import org.koin.android.ext.android.inject

class ViewPagerItemFragmentMainCourseDetail: Fragment() {

    private lateinit var adapter: RecyclerAdapterMainCourseDetail // 리사이클러뷰 어댑터

    private val mainAPI : MainAPI by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.viewpager_item_main_course_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerView()
        setTabListener()

    }

    // 리사이클러뷰 데이터 받아오기 (코스 정보들)


    // 리사이클러뷰 설정
    private fun setRecyclerView() {
        var list = listOf("12", "@3", "33")

        // @수민 추가
        mainAPI.listMainCourses(2).callback(
            successCallback = {
//                var list = listOf<Course>((it.data?.getOrNull(0)?.getOrNull(0))

                var course = it.data?.map {
                    var courseList = it.getOrNull(0)?.info?.map {
                        it.toEntity()
                    }

                    adapter = RecyclerAdapterMainCourseDetail(this.context!!, courseList!!)
                    main_course_detail_recycler_new_popular.adapter = adapter

                    toast("data success")
                }


//                adapter = RecyclerAdapterMainCourseDetail(this.context!!, )
            },
            failCallback = {
                toast("fail")
            },
            errorCallback = {
                toast(it.message!!)
            }
        )


//        adapter = RecyclerAdapterMainCourseDetail(this.context!!, list)

        val itemDeco = RecyclerItemDecorationVertical(this.context!!)
        main_course_detail_recycler_new_popular.addItemDecoration(itemDeco)

    }

    // 탭 선택시 리스너 설정
    private fun setTabListener() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab?.position

                if (pos == 0) {
                    toast("최신순")

                    // TODO RecyclerView Item 최신순으로 바뀌게
                }
                else if (pos == 1) {
                    toast("인기순")

                    // TODO RecyclerView Item 인기순으로 바뀌게
                }
            }

        })
    }
}