package korea.seoul.pickple.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.api.response.mypage.ListMyLikePlaceResponse
import korea.seoul.pickple.databinding.ItemPlaceNavigationBindingBinding

/**
 * Created by mj on 26, September, 2019
 */

class NavigationPickPlaceAdapter(private val mViewModel : NavigationViewModel) : RecyclerView.Adapter<NavigationPickPlaceAdapter.NavigationPickPlaceHolder>() {

    var items: List<ListMyLikePlaceResponse.Data.PlaceDTO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationPickPlaceHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaceNavigationBindingBinding.inflate(inflater, parent, false)
        binding.vm = mViewModel

        return NavigationPickPlaceHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NavigationPickPlaceHolder, position: Int) = holder.bind(items[position])


    inner class NavigationPickPlaceHolder(private val binding: ItemPlaceNavigationBindingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app:recyclerview_place_item_navigation_items")
fun RecyclerView.setItems(items: List<ListMyLikePlaceResponse.Data.PlaceDTO>) {
    (adapter as? NavigationPickPlaceAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}