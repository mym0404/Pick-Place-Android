package korea.seoul.pickple.ui.course.place_detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}

@BindingAdapter("imageList")
fun ViewPager2.setImageList(images: List<String>) {
    (adapter as? PlaceDetailViewPagerAdapter)?.apply {
        data = images
        notifyDataSetChanged()
    }
}