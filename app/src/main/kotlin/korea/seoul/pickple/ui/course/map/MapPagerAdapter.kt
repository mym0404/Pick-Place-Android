package korea.seoul.pickple.ui.course.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ItemMapPagerBinding
class MapPagerAdapter : RecyclerView.Adapter<MapPagerAdapter.MapPagerHolder>() {

    var items: List<Place> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapPagerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMapPagerBinding.inflate(inflater, parent, false)

        return MapPagerHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MapPagerHolder, position: Int) = holder.bind(items[position])


    inner class MapPagerHolder(private val binding: ItemMapPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app:viewpager2_map_pager_item")
fun ViewPager2.setItems(items: List<Place>) {
    (adapter as? MapPagerAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}