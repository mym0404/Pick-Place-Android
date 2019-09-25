package korea.seoul.pickple.ui.navigation.review

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import korea.seoul.pickple.R

class RecyclerAdapterNavigationReview(val ctx: Context, var data:List<String>): RecyclerView.Adapter<RecyclerAdapterNavigationReview.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_navigation_review_list,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {

        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var course_img = itemView.findViewById<ImageView>(R.id.item_navi_review_list_img_review)
        var heart_num = itemView.findViewById<TextView>(R.id.item_navi_review_list_tv_heart_num)
        var review_num = itemView.findViewById<TextView>(R.id.item_navi_review_list_tv_review_num) // "${review_num}개 리뷰"로 넣어야 함
        var review_time = itemView.findViewById<TextView>(R.id.item_navi_review_list_tv_time)
        var icon = itemView.findViewById<CircleImageView>(R.id.item_navi_review_list_civ_icon)
        var review_text = itemView.findViewById<TextView>(R.id.item_navi_review_list_tv_review)
    }
}