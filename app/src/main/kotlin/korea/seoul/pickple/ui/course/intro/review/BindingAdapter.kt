package korea.seoul.pickple.ui.course.intro.review

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Review

@BindingAdapter("reviews")
fun RecyclerView.setViewItem(list: List<Review>?) {
    (this.adapter as? ReviewAdapter)?.apply {
        data = list?: listOf()
        notifyDataSetChanged()
    }
}