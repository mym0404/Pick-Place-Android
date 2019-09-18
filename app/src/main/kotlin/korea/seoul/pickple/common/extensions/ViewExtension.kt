package korea.seoul.pickple.common.extensions

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import me.grantland.widget.AutofitTextView


/**
 * ViewPager2에서 양옆에 페이지들이 보이게 해주는 헬퍼 메서드
 * @author MJStudio
 */

fun ViewPager2.setShowSideItemsWithDimens(@DimenRes pageMarginPx: Int, @DimenRes offsetPx: Int) {
    this.setShowSideItems(
        resources.getDimensionPixelOffset(pageMarginPx),
        resources.getDimensionPixelOffset(offsetPx)
    )
}
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

fun View.startSimpleAnim() {

}



/**
 * Glide를 이용해 이미지를 로드하는 간단한 보조 함수
 * @author greedy0110
 * */
@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}

/**
 * 이하동문입니다
 * @author MJStudio
 */
@BindingAdapter("imageUrl")
fun ImageView.loadImage(uri: Uri?) {
    Glide.with(context)
        .load(uri)
        .into(this)
}

/**
 * AutofitTextView 에 바인딩을 위해
 * @author MJStudio
 */
@BindingAdapter("android:text")
fun AutofitTextView.setTextBinding(text : String) {
    this.text = text
}
@BindingAdapter("android:text")
fun AutofitTextView.setTextBinding(@StringRes id : Int) {
    this.text = resources.getString(id)
}

fun Fragment.toast(msg : String) {
    this.context?.toast(msg)
}
fun Context.toast(msg : String) {
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}