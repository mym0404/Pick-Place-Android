package korea.seoul.pickple.ui.navigation

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.annotation.AnimRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.ui.course.create.CourseCreateActivity
import korea.seoul.pickple.ui.course.create.search.CourseCreateSearchActivity
import korea.seoul.pickple.ui.course.map.MapActivity

sealed class NavigationArgs {


    //계속 추가 요망
    //일단 Nullable로 해놓음
    class MapActivityArg(val course: Course?) : NavigationArgs() {
        companion object {
            const val MAP_ARG_COURSE = "MAP_ARG_COURSE"
        }
    }

    class CourseCreateArgs() : NavigationArgs() {

    }

    class CourseCreateSearchArg() : NavigationArgs() {

    }


}

//계속 추가 요망
/**
 * 액티비티에서 intent를 파싱할 때 쓰셈
 */
fun MapActivity.parseIntent(intent: Intent) = NavigationArgs.MapActivityArg(intent.getParcelableExtra<Course?>(NavigationArgs.MapActivityArg.MAP_ARG_COURSE))

fun CourseCreateActivity.parseIntent(intent: Intent) = NavigationArgs.CourseCreateArgs()
fun CourseCreateSearchActivity.parseIntent(intent: Intent) = NavigationArgs.CourseCreateSearchArg()


/**
 * 네비게이션 할 때 쓰셈
 */
fun navigate(
    curActivity: Activity, arg: NavigationArgs, forResultStartRequestCode: Int? = null,
    @AnimRes enterResId: Int? = null,
    @AnimRes exitResId: Int? = null,
    sharedElementPairs: List<Pair<View, String>>? = null
) {

    //계속 추가 요망
    val intent = when (arg) {
        is NavigationArgs.MapActivityArg -> Intent(curActivity, MapActivity::class.java).apply {
            putExtra(NavigationArgs.MapActivityArg.MAP_ARG_COURSE, arg.course)
        }
        is NavigationArgs.CourseCreateArgs -> Intent(curActivity, CourseCreateActivity::class.java)
        is NavigationArgs.CourseCreateSearchArg -> Intent(curActivity, CourseCreateSearchActivity::class.java)
    }



    if (enterResId != null && exitResId != null) {

        if (forResultStartRequestCode != null) {
            curActivity.startActivityForResult(intent, forResultStartRequestCode, ActivityOptionsCompat.makeCustomAnimation(curActivity, enterResId, exitResId).toBundle())
        } else {
            curActivity.startActivity(intent, ActivityOptionsCompat.makeCustomAnimation(curActivity, enterResId, exitResId).toBundle())
        }

        return
    } else if (sharedElementPairs != null) {
        if (forResultStartRequestCode != null) {
            curActivity.startActivityForResult(intent, forResultStartRequestCode, ActivityOptionsCompat.makeSceneTransitionAnimation(curActivity, *sharedElementPairs.toTypedArray()).toBundle())
        } else {
            curActivity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(curActivity, *sharedElementPairs.toTypedArray()).toBundle())
        }
        return
    } else {
        if (forResultStartRequestCode != null) {
            curActivity.startActivityForResult(intent, forResultStartRequestCode)
        } else {
            curActivity.startActivity(intent)
        }
        return
    }
}