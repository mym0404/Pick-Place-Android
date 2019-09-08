package korea.seoul.pickple.ui.course.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ItemMapPagerBinding
class MapPagerAdapter : RecyclerView.Adapter<MapPagerAdapter.MapPagerHolder>() {

    //TODO DUMMY
    var items: List<Place> = listOf(
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null),
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null),
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null),
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null),
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null),
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null),
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null),
        Place(-1,Place.Type.FOOD,"name","desc",null,null,null)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapPagerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMapPagerBinding.inflate(inflater, parent, false)

        return MapPagerHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MapPagerHolder, position: Int) = holder.bind(items[position])


    inner class MapPagerHolder(private val binding: ItemMapPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.index.text = String.format("%02d",layoutPosition + 1)
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}