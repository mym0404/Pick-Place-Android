package korea.seoul.pickple.common.util

import android.util.Log
import korea.seoul.pickple.BuildConfig

/**
 * Created by mj on 23, September, 2019
 */

fun debugE(tag: String, message: Any?) {
    if (BuildConfig.DEBUG)
        Log.e(tag, "\uD83C\uDF40" + message.toString())
}

fun debugE(message: Any?) {

    debugE("DEBUG", message)
}