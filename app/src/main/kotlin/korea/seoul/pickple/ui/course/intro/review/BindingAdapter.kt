package korea.seoul.pickple.ui.course.intro.review

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Review

@BindingAdapter("reviews", "isFullReview")
fun RecyclerView.setViewItem(list: List<Review>?, isFullReview: Boolean) {
    (this.adapter as? ReviewAdapter)?.apply {
        data = list?: listOf()

        // 만약 전체 리뷰 보는 상황이 아니라면 2개의 리뷰만 보여주자.
        if (!isFullReview) data = data.take(2)
        notifyDataSetChanged()
    }
}

@BindingAdapter("imageDrawableId")
fun ImageView.setImageDrawable(id: Int) {
    this.setImageResource(id)
}