package korea.seoul.pickple.common.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by mj on 22, September, 2019
 */

class LinearItemDecoration (private val context : Context, private val spacing : Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.getChildLayoutPosition(view)

        outRect.right = context.getPixelFromDP(spacing)

        if(pos == 0) {
            outRect.left = context.getPixelFromDP(spacing)
        }
    }
}