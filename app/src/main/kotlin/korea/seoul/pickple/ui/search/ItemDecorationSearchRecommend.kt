package korea.seoul.pickple.ui.search

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.common.util.getPixelFromDP

class ItemDecorationSearchRecommend(private val context : Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.getChildLayoutPosition(view) // 포지션 가져오기

        outRect.bottom = context.getPixelFromDP(12)
    }
}