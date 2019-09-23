package korea.seoul.pickple.ui.navigation.review

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import korea.seoul.pickple.common.util.getPixelFromDP

class ItemDecorationReviewList(private val context : Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.getChildLayoutPosition(view) // 포지션 가져오기

        outRect.bottom = context.getPixelFromDP(24)
        outRect.right = context.getPixelFromDP(16)
        outRect.left = context.getPixelFromDP(16)

        if (pos == 0) {
            outRect.top = context.getPixelFromDP(11)
        }
    }

}