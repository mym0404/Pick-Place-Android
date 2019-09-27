package korea.seoul.pickple.ui.course.intro.all_course

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.R
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ItemAllCourseBinding

class ShowAllCourseAdapter(
    private val context: Context,
    var data: List<Place>,
    private val showAllCoursesViewModel: ShowAllCoursesViewModel
) : RecyclerView.Adapter<ShowAllCourseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAllCourseBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.item_all_course, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.place = data[position]
        holder.binding.containerItemAllCourse.setOnClickListener {
            showAllCoursesViewModel.clickIndex(position)
        }
    }

    inner class ViewHolder(val binding: ItemAllCourseBinding): RecyclerView.ViewHolder(binding.root)
}