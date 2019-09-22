package korea.seoul.pickple.ui.course.intro.all_course

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Course

@BindingAdapter("courses")
fun RecyclerView.setAllCourse(courses: List<Course>?) {
    (adapter as? ShowAllCourseAdapter)?.apply {
        data = courses?: listOf()
        notifyDataSetChanged()
    }
}