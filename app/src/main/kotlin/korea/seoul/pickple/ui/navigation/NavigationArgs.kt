package korea.seoul.pickple.ui.navigation

import android.app.Activity
import android.content.Intent
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.ui.course.map.MapActivity

sealed class NavigationArgs {


    //계속 추가 요망
    class MapActivityArg(val course: Course) : NavigationArgs() {
        companion object {
            const val MAP_ARG_COURSE = "MAP_ARG_COURSE"
        }
    }


}

fun navigate(activity: Activity, arg: NavigationArgs) {

    val intent = when (arg) {
        is NavigationArgs.MapActivityArg -> Intent(activity, MapActivity::class.java).apply {
            putExtra(NavigationArgs.MapActivityArg.MAP_ARG_COURSE, arg.course)
        }
    }

    activity.startActivity(intent)
}

//계속 추가 요망
fun MapActivity.parseIntent(intent : Intent) = NavigationArgs.MapActivityArg(intent.getParcelableExtra<Course>(NavigationArgs.MapActivityArg.MAP_ARG_COURSE))