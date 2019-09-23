package korea.seoul.pickple.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {

    private lateinit var resultAdapter : RecyclerAdapterSearchResult
    private lateinit var recommendAdapter : RecyclerAdapterSearchRecommend

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setListener()
        setRecycler()
    }

    private fun setRecycler() {
        // TODO 수민) 데이터 바꾸기

        var resultList = listOf("123e", "!23", "@3")
        var recommendWordList = listOf("한국 근대 역사 코스", "데이트코스", "서울역 오래가게 탐방 코스")

        // result
        resultAdapter = RecyclerAdapterSearchResult(this, resultList)
        act_search_rv_search_result.adapter = resultAdapter

        val itemDecorationSearchResult = ItemDecorationSearchResult(this)
        act_search_rv_search_result.addItemDecoration(itemDecorationSearchResult)

        // recommend word
        recommendAdapter = RecyclerAdapterSearchRecommend(this, recommendWordList)
        act_search_rv_popular_course.adapter = recommendAdapter

        val itemDecorationSearchRecommend = ItemDecorationSearchRecommend(this)
        act_search_rv_popular_course.addItemDecoration(itemDecorationSearchRecommend)

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
                }


            }

            true
        }
    }
}
