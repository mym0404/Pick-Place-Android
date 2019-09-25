package korea.seoul.pickple.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import korea.seoul.pickple.R
import korea.seoul.pickple.data.entity.Course

class RecyclerAdapterSearchResult(val ctx: Context, var data:List<Course>): RecyclerView.Adapter<RecyclerAdapterSearchResult.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_search_result,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            // TODO 수민 실제 데이터로 바꾸기


        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var course_img = itemView.findViewById<ImageView>(R.id.item_search_result_img_result)
        var course_icon = itemView.findViewById<CircleImageView>(R.id.item_search_civ_icon)
        var course_tag = itemView.findViewById<TextView>(R.id.item_search_result_tv_tag)
        var course_name = itemView.findViewById<TextView>(R.id.item_search_result_course_name)
        var heart_num = itemView.findViewById<TextView>(R.id.item_search_result_tv_heart)
        var review_num = itemView.findViewById<TextView>(R.id.item_search_result_tv_review)

    }
}