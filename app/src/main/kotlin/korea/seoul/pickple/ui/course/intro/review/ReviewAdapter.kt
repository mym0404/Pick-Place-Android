package korea.seoul.pickple.ui.course.intro.review

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.databinding.ItemReviewBinding

class ReviewAdapter (
    private val context: Context,
    var data: List<Review>
) : RecyclerView.Adapter<ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val reviewItemBinding = ItemReviewBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ReviewViewHolder(reviewItemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.mBinding.review = data[position]
    }
}