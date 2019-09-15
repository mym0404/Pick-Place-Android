package korea.seoul.pickple.ui.course.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.common.widget.ItemTouchHelperAdapter
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ItemCourseCreateBinding
import java.util.*

class CourseCreateAdapter(private val vm : CourseCreateViewModel,private val lifeCycleOwner : LifecycleOwner) : RecyclerView.Adapter<CourseCreateAdapter.CourseCreateHolder>(), ItemTouchHelperAdapter {

    var items: List<Place> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCreateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseCreateBinding.inflate(inflater, parent, false)
        binding.vm = vm
        binding.lifecycleOwner = lifeCycleOwner

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

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        items -= items[position]
        notifyItemRemoved(position)
    }
}

@BindingAdapter("app_recyclerview_course_create_items")
fun RecyclerView.setItems(items: List<Place>) {
    (adapter as? CourseCreateAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}