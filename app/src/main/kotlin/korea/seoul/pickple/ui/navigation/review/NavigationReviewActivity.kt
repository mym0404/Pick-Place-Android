package korea.seoul.pickple.ui.navigation.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import korea.seoul.pickple.R
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.api.MyPageAPI
import korea.seoul.pickple.databinding.ActivityNavigationBinding
import korea.seoul.pickple.ui.navigation.NavigationReviewAdapter
import kotlinx.android.synthetic.main.activity_navigation_review_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationReviewActivity : AppCompatActivity() {

    private lateinit var reviewAdapter : RecyclerAdapterNavigationReview
    private val MyPageAPI : MyPageAPI by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_review_list)

        initView()
        initRecyclerView()
    }

    private fun initView() {
        act_navi_review_list_ibtn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initRecyclerView() {
        getMyReviewList()

        val itemDecoration = ItemDecorationReviewList(this)
        act_navi_review_list_recycler_review.addItemDecoration(itemDecoration)
    }

    private fun getMyReviewList() {
        MyPageAPI.listMyReview().callback (
            successCallback = {
                var myReviewList = it.data?.map {
                    it.info.getOrNull(0)?.toEntity()
                }?.filterNotNull() ?: listOf() // filterNotNull()은 null이 아닌 것만 filterㄴ해준다.

                reviewAdapter = RecyclerAdapterNavigationReview(this, myReviewList)
                act_navi_review_list_recycler_review.adapter = reviewAdapter

                reviewAdapter.notifyDataSetChanged()
            },
            failCallback = {

            },
            errorCallback = {

            }
        )
    }
}
