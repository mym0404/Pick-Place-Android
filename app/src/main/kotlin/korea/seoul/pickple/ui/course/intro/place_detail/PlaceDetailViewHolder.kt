package korea.seoul.pickple.ui.course.intro.place_detail

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.loadImage

class PlaceDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val placeImage: ImageView? by lazy { view.findViewById<ImageView?>(R.id.imageItemPlaceDetailPager) }

    fun bind(imageUrl: String) {
        placeImage?.loadImage(imageUrl)
    }
}