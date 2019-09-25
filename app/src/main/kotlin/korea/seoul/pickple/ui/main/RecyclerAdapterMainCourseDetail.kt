package korea.seoul.pickple.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.loadImage
import korea.seoul.pickple.common.util.IntentUtil
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.ui.NavigationArgs
import korea.seoul.pickple.ui.course.intro.CourseIntroActivity
import korea.seoul.pickple.ui.course.intro.unite_intro.UniteCourseActivity
import korea.seoul.pickple.ui.navigate
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.inject

// TODO 수민 data를 List<Course>로 바꾸기
class RecyclerAdapterMainCourseDetail(val ctx: Context, var data:List<Course>): RecyclerView.Adapter<RecyclerAdapterMainCourseDetail.Holder>(), KoinComponent {

    val intentUtil : IntentUtil by inject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_main_course_detail,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            // TODO 수민 실제 데이터로 바꾸기
            val currentItem = data[position]

            // 데이터 값 넣기
            Glide.with(ctx)
                .load(currentItem.thumbnail)
                .into(course_img)
            Glide.with(ctx)
                .load(currentItem.thumbnail)
                .into(course_icon)

            val tags = currentItem.tagList.toString()
            course_tag.text = tags

            course_name.text = currentItem.name


            course_detail_card.setOnClickListener{
                navigate(ctx as Activity, NavigationArgs.CourseIntroArg(3))
            }

            course_share_btn.setOnClickListener {
                intentUtil.share(ctx as Activity, "https://pickple.page.link/jTpt", "코스를 공유하세요")
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val course_detail_card = itemView.findViewById<ConstraintLayout>(R.id.item_main_course_card)
        val course_img = itemView.findViewById<ImageView>(R.id.item_main_course_detail_img)
        val course_icon = itemView.findViewById<CircleImageView>(R.id.item_main_course_detail_civ_icon)
        val course_tag = itemView.findViewById<TextView>(R.id.item_main_course_detail_tv_tag)
        val course_name = itemView.findViewById<TextView>(R.id.item_main_course_detail_tv_course_name)
        val course_places = itemView.findViewById<TextView>(R.id.item_main_course_detail_tv_places)
        val course_share_btn = itemView.findViewById<ImageButton>(R.id.item_main_course_detail_ibtn_share)
    }
}