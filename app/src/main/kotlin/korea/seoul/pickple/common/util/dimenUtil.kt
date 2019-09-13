package korea.seoul.pickple.common.util

import android.content.Context
import kotlin.math.roundToInt

fun Context.getPixelFromDP(dp : Int) : Int = (resources.displayMetrics.density * dp).roundToInt()
fun Context.getDPFromPixel(pixel : Int) : Int = (pixel / resources.displayMetrics.density).roundToInt()