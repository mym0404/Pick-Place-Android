package korea.seoul.pickple.common.extensions

import androidx.annotation.DimenRes
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2


fun ViewPager2.setShowSideItemsWithDimens(@DimenRes pageMarginPx: Int, @DimenRes offsetPx: Int) {
    this.setShowSideItems(
        resources.getDimensionPixelOffset(pageMarginPx),
        resources.getDimensionPixelOffset(offsetPx)
    )
}

/**
 * ViewPager2에서 양옆에 페이지들이 보이게 해주는 헬퍼 메서드
 */
fun ViewPager2.setShowSideItems(pageMarginPx: Int, offsetPx: Int) {

    clipToPadding = false
    clipChildren = false
    offscreenPageLimit = 3

    setPageTransformer { page, position ->

        val offset = position * -(2 * offsetPx + pageMarginPx)

        if (this.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.translationX = -offset
            } else {
                page.translationX = offset
            }
        } else {
            page.translationY = offset
        }
    }
}