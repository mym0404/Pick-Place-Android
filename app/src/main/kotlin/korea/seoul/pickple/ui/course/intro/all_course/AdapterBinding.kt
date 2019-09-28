package korea.seoul.pickple.ui.course.intro.all_course

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place

@BindingAdapter("livePlaces")
fun RecyclerView.setAllCourse(places: List<Place>?) {
    (adapter as? ShowAllCourseAdapter)?.apply {
        data = places?: listOf()
        notifyDataSetChanged()
    }
}