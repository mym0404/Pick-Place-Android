package korea.seoul.pickple.ui.course.intro.place_detail

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("imageList")
fun ViewPager2.setImageList(images: List<String>?) {
    images ?: return
    (adapter as? PlaceDetailViewPagerAdapter)?.apply {
        data = images
        notifyDataSetChanged()
    }
}