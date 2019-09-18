package korea.seoul.pickple.ui.course.intro.place_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.R

class PlaceDetailViewPagerAdapter(
    private val context: Context,
    var data: List<String>
): RecyclerView.Adapter<PlaceDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceDetailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_place_detail_pager, parent, false)
        return PlaceDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PlaceDetailViewHolder, position: Int) {
        holder.bind(data[position])
    }
}