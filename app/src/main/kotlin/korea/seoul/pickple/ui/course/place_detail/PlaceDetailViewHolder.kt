package korea.seoul.pickple.ui.course.place_detail

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.R

class PlaceDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val placeImage: ImageView? by lazy { view.findViewById<ImageView?>(R.id.imageItemPlaceDetailPager) }

    fun bind(imageUrl: String) {
        placeImage?.loadImage(imageUrl)
    }
}