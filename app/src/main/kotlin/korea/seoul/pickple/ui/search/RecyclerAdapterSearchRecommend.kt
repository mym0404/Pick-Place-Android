package korea.seoul.pickple.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import korea.seoul.pickple.R

class RecyclerAdapterSearchRecommend(val ctx: Context, var data:List<String>): RecyclerView.Adapter<RecyclerAdapterSearchRecommend.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.item_search_recommend_word,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.run {
            recommend_word.text = data[position]
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var recommend_word = itemView.findViewById<TextView>(R.id.item_search_recommend_word_tv_word)
    }
}