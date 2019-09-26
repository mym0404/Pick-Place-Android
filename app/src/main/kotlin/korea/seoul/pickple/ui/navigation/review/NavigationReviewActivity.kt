package korea.seoul.pickple.ui.navigation.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import korea.seoul.pickple.R
import korea.seoul.pickple.databinding.ActivityNavigationBinding
import korea.seoul.pickple.ui.navigation.NavigationReviewAdapter
import kotlinx.android.synthetic.main.activity_navigation_review_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationReviewActivity : AppCompatActivity() {

    private lateinit var reviewAdapter : RecyclerAdapterNavigationReview

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
        reviewAdapter = RecyclerAdapterNavigationReview(this, listOf("12", "!23", "34"))
        act_navi_review_list_recycler_review.adapter = reviewAdapter

        val itemDecoration = ItemDecorationReviewList(this)
        act_navi_review_list_recycler_review.addItemDecoration(itemDecoration)
    }
}
