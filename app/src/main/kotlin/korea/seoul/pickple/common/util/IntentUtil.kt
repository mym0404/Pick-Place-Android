package korea.seoul.pickple.common.util

import android.app.Activity
import android.content.Intent

/**
 * Created by mj on 23, September, 2019
 */

class IntentUtil() {
    fun share(activity : Activity, url : String, subject : String) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "text/plain"
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, url)
        activity.startActivity(Intent.createChooser(i, "Share URL"))
    }
}