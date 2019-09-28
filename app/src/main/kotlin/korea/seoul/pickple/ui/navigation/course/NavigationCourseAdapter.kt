package korea.seoul.pickple.ui.navigation.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.data.api.response.mypage.ListMyCoursesResponse
import korea.seoul.pickple.databinding.ItemNavigationCourseBindingBinding

/**
 * Created by mj on 28, September, 2019
 */

class NavigationCourseAdapter(private val mViewModel : NavigationCourseViewModel) : RecyclerView.Adapter<NavigationCourseAdapter.NavigationCourseHolder>() {

    var items: List<ListMyCoursesResponse.Data.CourseDTO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationCourseHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNavigationCourseBindingBinding.inflate(inflater, parent, false)
        binding.vm = mViewModel

        val screenWidth = parent.context.resources.displayMetrics.widthPixels

        val lp = binding.root.layoutParams
        binding.root.layoutParams = lp.apply { height = (screenWidth/2 - 180) }

        return NavigationCourseHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NavigationCourseHolder, position: Int) = holder.bind(items[position])


    inner class NavigationCourseHolder(private val binding: ItemNavigationCourseBindingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }
}

@BindingAdapter("app:recyclerview_navigation_course_items_tiems")
fun RecyclerView.setItems(items: List<ListMyCoursesResponse.Data.CourseDTO>) {
    (adapter as? NavigationCourseAdapter)?.run {
        this.items = items
        this.notifyDataSetChanged()
    }
}