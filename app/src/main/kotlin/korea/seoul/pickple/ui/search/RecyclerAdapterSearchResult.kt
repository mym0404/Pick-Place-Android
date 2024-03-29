package korea.seoul.pickple.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.loadImage
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.course.intro.review.setImageDrawable
import korea.seoul.pickple.ui.navigate
import kotlin.random.Random

class RecyclerAdapterSearchResult(val ctx: Context, var data:List<Course>): RecyclerView.Adapter<RecyclerAdapterSearchResult.Holder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_search_result,parent,false)



        return Holder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            // TODO 수민 실제 데이터로 바꾸기
            var currentData = data[position]

            course_img.loadImage(currentData.thumbnail)

            when (currentData.type?.type) {
                0 -> {
                    course_icon.setImageResource(R.drawable.oldshop_course_icon)
                }
                1 -> {
                    course_icon.setImageResource(R.drawable.oldshop_tradition_icon)
                }
                2 -> {
                    course_icon.setImageResource(R.drawable.user_course_icon)
                }
            }

            course_tag.text = currentData.tagList?.getOrNull(0)
            course_name.text = currentData.name
            heart_num.text = currentData.likeCount.toString()
            review_num.text = "${currentData.review_count}개의 후기"

            result_card.setOnClickListener {
                navigate(ctx as AppCompatActivity, NavigationArgs.CourseIntroArg(currentData.id))
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var result_card = itemView.findViewById<CardView>(R.id.item_search_result_card_view)
        var course_img = itemView.findViewById<ImageView>(R.id.item_search_result_img_result)
        var course_icon = itemView.findViewById<ImageView>(R.id.item_search_civ_icon)
        var course_tag = itemView.findViewById<TextView>(R.id.item_search_result_tv_tag)
        var course_name = itemView.findViewById<TextView>(R.id.item_search_result_course_name)
        var heart_num = itemView.findViewById<TextView>(R.id.item_search_result_tv_heart)
        var review_num = itemView.findViewById<TextView>(R.id.item_search_result_tv_review)

    }
}