package korea.seoul.pickple.ui.navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.databinding.ItemCourseNavigationBindingBinding

/**
 * Created by mj on 22, September, 2019
 */

class NavigationCourseAdapter(private val mViewModel : NavigationViewModel) : RecyclerView.Adapter<NavigationCourseAdapter.NavigationCourseHolder>() {

    var items: List<Course> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationCourseHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseNavigationBindingBinding.inflate(inflater, parent, false)
        binding.vm = mViewModel

        return NavigationCourseHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NavigationCourseHolder, position: Int) = holder.bind(items[position])


    inner class NavigationCourseHolder(private val binding: ItemCourseNavigationBindingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app:recyclerview_course_navigation_items")
fun RecyclerView.setItems(items: List<Course>) {
    (adapter as? NavigationCourseAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}