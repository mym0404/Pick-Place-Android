package korea.seoul.pickple.ui.navigation.pickplace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.api.dto.CourseDTO
import korea.seoul.pickple.databinding.ItemNavigationPickplaceBinding

/**
 * Created by mj on 28, September, 2019
 */
class NavigationPickPlaceAdapter(private val vm : NavigationPickPlaceViewModel) : RecyclerView.Adapter<NavigationPickPlaceAdapter.NavigationPickPlaceHolder>() {

    var items: List<CourseDTO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationPickPlaceHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNavigationPickplaceBinding.inflate(inflater, parent, false)
        binding.vm = vm

        return NavigationPickPlaceHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NavigationPickPlaceHolder, position: Int) = holder.bind(items[position])


    inner class NavigationPickPlaceHolder(private val binding: ItemNavigationPickplaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app:recyclerview_navigation_pickplace_items")
fun RecyclerView.setItems(items: List<CourseDTO>) {
    (adapter as? NavigationPickPlaceAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}