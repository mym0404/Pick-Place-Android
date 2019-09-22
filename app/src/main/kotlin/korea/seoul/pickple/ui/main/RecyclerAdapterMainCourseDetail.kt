package korea.seoul.pickple.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import korea.seoul.pickple.R
import korea.seoul.pickple.data.entity.Course

// TODO 수민 data를 List<Course>로 바꾸기
class RecyclerAdapterMainCourseDetail(val ctx: Context, var data:List<String>): RecyclerView.Adapter<RecyclerAdapterMainCourseDetail.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_main_course_detail,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            // TODO 수민 실제 데이터로 바꾸기
            val currentItem = data[position]

        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val course_img = itemView.findViewById<ImageView>(R.id.item_main_course_detail_img)
        val course_icon = itemView.findViewById<CircleImageView>(R.id.item_main_course_detail_civ_icon)
        val course_tag = itemView.findViewById<TextView>(R.id.item_main_course_detail_tv_tag)
        val course_name = itemView.findViewById<TextView>(R.id.item_main_course_detail_tv_course_name)
        val course_places = itemView.findViewById<TextView>(R.id.item_main_course_detail_tv_places)
    }
}