package korea.seoul.pickple.ui.course.place_detail

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("imageList")
fun ViewPager2.setImageList(images: List<String>) {
    (adapter as? PlaceDetailViewPagerAdapter)?.apply {
        data = images
        notifyDataSetChanged()
    }
}