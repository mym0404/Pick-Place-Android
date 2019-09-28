package korea.seoul.pickple.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.api.OpenAPIDTO
import korea.seoul.pickple.data.entity.SeoulNews
import korea.seoul.pickple.databinding.ItemSeoulNewsBinding

/**
 * Created by mj on 22, September, 2019
 */

class SeoulNewsAdapter(private val vm : NavigationViewModel) : RecyclerView.Adapter<SeoulNewsAdapter.SeoulNewsHolder>() {

    var items: List<OpenAPIDTO.CulturalEventInfo.Row> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeoulNewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSeoulNewsBinding.inflate(inflater, parent, false)
        binding.vm = this.vm

        return SeoulNewsHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SeoulNewsHolder, position: Int) = holder.bind(items[position])


    inner class SeoulNewsHolder(private val binding: ItemSeoulNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app:recyclerview_seoul_news_items")
fun RecyclerView.setItems(items: List<OpenAPIDTO.CulturalEventInfo.Row>) {
    (adapter as? SeoulNewsAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}