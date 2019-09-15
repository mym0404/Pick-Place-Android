package korea.seoul.pickple.ui.course.create.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ItemCourseCreateSearchBinding

class CourseCreateSearchAdapter : RecyclerView.Adapter<CourseCreateSearchAdapter.CourseCreateSearchHolder>() {


    private val diff = object : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer<Place>(this,diff)

    fun submitItems(items : List<Place>) {
        differ.submitList(items)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCreateSearchHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseCreateSearchBinding.inflate(inflater, parent, false)

        return CourseCreateSearchHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: CourseCreateSearchHolder, position: Int) = holder.bind(differ.currentList[position])


    inner class CourseCreateSearchHolder(private val binding: ItemCourseCreateSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app_recyclerview_course_search_items")
fun RecyclerView.setItems(items: List<Place>) {
    (adapter as? CourseCreateSearchAdapter)?.run {
        this.submitItems(items)
    }
}