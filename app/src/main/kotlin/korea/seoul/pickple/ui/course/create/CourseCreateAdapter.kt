package korea.seoul.pickple.ui.course.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ItemCourseCreateBinding

class CourseCreateAdapter : RecyclerView.Adapter<CourseCreateAdapter.CourseCreateHolder>() {

    var items: List<Place> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCreateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseCreateBinding.inflate(inflater, parent, false)

        return CourseCreateHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CourseCreateHolder, position: Int) = holder.bind(items[position])


    inner class CourseCreateHolder(private val binding: ItemCourseCreateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app_recyclerview_course_create_items")
fun RecyclerView.setItems(items: List<Place>) {
    (adapter as? CourseCreateAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}