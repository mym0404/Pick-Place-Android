package korea.seoul.pickple.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.toast
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.api.MainAPI
import korea.seoul.pickple.data.entity.Course
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject


class SearchActivity : AppCompatActivity() {

    private lateinit var resultAdapter : RecyclerAdapterSearchResult
    private lateinit var recommendAdapter : RecyclerAdapterSearchRecommend

    // 수민) 서버 통신,,?
    private val mainAPI : MainAPI by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setListener()
        setRecycler()
    }

    // 서버 통신
    private fun getSearchResult(keyword : String) {
        mainAPI.listMainSearch(keyword).callback(
            successCallback = {
                var searchResultList = it.data?.map {
                    it.toEntity()
                } ?: listOf()

                resultAdapter = RecyclerAdapterSearchResult(this, searchResultList)
                act_search_rv_search_result.adapter = resultAdapter

                resultAdapter.notifyDataSetChanged()
            },
            failCallback = {
                toast("search failed")
            },
            errorCallback = {
                toast(it.message!!)
            }
        )
    }

    private fun setRecycler() {
        // TODO 수민) 데이터 바꾸기
        var recommendWordList = listOf("한국 근대 역사 코스", "데이트코스", "서울역 오래가게 탐방 코스")

        // result
//        resultAdapter = RecyclerAdapterSearchResult(this, resultList)
//        act_search_rv_search_result.adapter = resultAdapter
//
//        val itemDecorationSearchResult = ItemDecorationSearchResult(this)
//        act_search_rv_search_result.addItemDecoration(itemDecorationSearchResult)

        // recommend word
        recommendAdapter = RecyclerAdapterSearchRecommend(this, recommendWordList)
        act_search_rv_popular_course.adapter = recommendAdapter

        val itemDecorationSearchRecommend = ItemDecorationSearchRecommend(this)
        act_search_rv_popular_course.addItemDecoration(itemDecorationSearchRecommend)

        val itemDecorationSearchResult = ItemDecorationSearchResult(this)
        act_search_rv_search_result.addItemDecoration(itemDecorationSearchResult)
    }

    private fun setListener() {
        // back button listener
        act_search_img_back.setOnClickListener {
            finish()
        }

        // keyboard search button listener
        act_search_et_search.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {

                EditorInfo.IME_ACTION_SEARCH ->
                {
                    act_search_constraint_popular_course.visibility = View.GONE
                    act_search_constraint_search_result.visibility = View.VISIBLE

                    getSearchResult(act_search_et_search.text.toString())
                }


            }

            true
        }
    }
}
