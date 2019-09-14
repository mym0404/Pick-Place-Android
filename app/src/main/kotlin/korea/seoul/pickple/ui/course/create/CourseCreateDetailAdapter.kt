package korea.seoul.pickple.ui.course.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ItemCourseCreateDetaiPagerBinding

class CourseCreateDetailAdapter : RecyclerView.Adapter<CourseCreateDetailAdapter.CourseCreateDetailHolder>() {

    var items: List<Place> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCreateDetailHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseCreateDetaiPagerBinding.inflate(inflater, parent, false)

        return CourseCreateDetailHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CourseCreateDetailHolder, position: Int) = holder.bind(items[position])


    inner class CourseCreateDetailHolder(private val binding: ItemCourseCreateDetaiPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app_recyclerview_course_create_detail_items")
fun ViewPager2.setItems(items: List<Place>) {
    (adapter as? CourseCreateDetailAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}