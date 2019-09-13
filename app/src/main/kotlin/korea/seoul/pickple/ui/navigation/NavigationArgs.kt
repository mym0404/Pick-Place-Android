package korea.seoul.pickple.ui.navigation

import android.app.Activity
import android.content.Intent
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.ui.course.map.MapActivity

sealed class NavigationArgs {


    //계속 추가 요망
    //일단 Nullable로 해놓음
    class MapActivityArg(val course: Course?) : NavigationArgs() {
        companion object {
            const val MAP_ARG_COURSE = "MAP_ARG_COURSE"
        }
    }


}

//계속 추가 요망
/**
 * 액티비티에서 intent를 파싱할 때 쓰셈
 */
fun MapActivity.parseIntent(intent : Intent) =   NavigationArgs.MapActivityArg(intent.getParcelableExtra<Course?>(NavigationArgs.MapActivityArg.MAP_ARG_COURSE))


/**
 * 네비게이션 할 때 쓰셈
 */
fun Activity.navigateWithArgs(arg: NavigationArgs) = navigate(this,arg)
fun navigate(curActivity: Activity, arg: NavigationArgs) {

    val intent = when (arg) {
        is NavigationArgs.MapActivityArg -> Intent(curActivity, MapActivity::class.java).apply {
            putExtra(NavigationArgs.MapActivityArg.MAP_ARG_COURSE, arg.course)
        }
    }

    curActivity.startActivity(intent)
}