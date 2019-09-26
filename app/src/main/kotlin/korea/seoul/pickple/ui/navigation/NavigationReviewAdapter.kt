package korea.seoul.pickple.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.api.response.mypage.ListMyReviewResponse
import korea.seoul.pickple.databinding.ItemNavigationReviewBinding

/**
 * Created by mj on 22, September, 2019
 */

class NavigationReviewAdapter(private val vm : NavigationViewModel) : RecyclerView.Adapter<NavigationReviewAdapter.NavigationReviewHolder>() {

    var items: List<ListMyReviewResponse.Data.ReviewDTO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationReviewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNavigationReviewBinding.inflate(inflater, parent, false)
        binding.vm = vm

        return NavigationReviewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NavigationReviewHolder, position: Int) = holder.bind(items[position])


    inner class NavigationReviewHolder(private val binding: ItemNavigationReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app:recyclerview_navigation_review_items")
fun RecyclerView.setItems(items: List<ListMyReviewResponse.Data.ReviewDTO>) {
    (adapter as? NavigationReviewAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}