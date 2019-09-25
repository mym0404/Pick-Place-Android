package korea.seoul.pickple.ui.search

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.common.util.getPixelFromDP

class ItemDecorationSearchResult(private val context : Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.getChildLayoutPosition(view) // 포지션 가져오기

        outRect.bottom = context.getPixelFromDP(24)
        outRect.right = context.getPixelFromDP(32)
        outRect.left = context.getPixelFromDP(32)

        if (pos == 0) {
            outRect.top = context.getPixelFromDP(10)
        }
    }

}