package korea.seoul.pickple.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.annotation.AnimRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.ui.NavigationArgs.CourseCreateArgs.Companion.COURSE_CREATE_ARG_COURSE
import korea.seoul.pickple.ui.NavigationArgs.CourseCreateArgs.Companion.COURSE_CREATE_ARG_DESCRIPTION
import korea.seoul.pickple.ui.NavigationArgs.CourseCreateArgs.Companion.COURSE_CREATE_ARG_ONLY_SHOW
import korea.seoul.pickple.ui.NavigationArgs.CourseCreateArgs.Companion.COURSE_CREATE_ARG_TAGLIST
import korea.seoul.pickple.ui.NavigationArgs.CourseCreateArgs.Companion.COURSE_CREATE_ARG_THUMBNAIL
import korea.seoul.pickple.ui.NavigationArgs.CourseCreateArgs.Companion.COURSE_CREATE_ARG_TITLE
import korea.seoul.pickple.ui.course.create.CourseCreateActivity
import korea.seoul.pickple.ui.course.create.intro.CourseCreateIntroActivity
import korea.seoul.pickple.ui.course.create.search.CourseCreateSearchActivity
import korea.seoul.pickple.ui.course.intro.CourseIntroActivity
import korea.seoul.pickple.ui.course.intro.all_course.ShowAllCoursesActivity
import korea.seoul.pickple.ui.course.intro.all_review.ShowAllReviewActivity
import korea.seoul.pickple.ui.course.map.MapActivity

sealed class NavigationArgs {

    //계속 추가 요망
    //일단 Nullable로 해놓음
    class MapActivityArg(val course: Course?) : NavigationArgs() {
        companion object {
            const val MAP_ARG_COURSE = "MAP_ARG_COURSE"
        }
    }

    class CourseCreateIntroAgs() : NavigationArgs() {

    }

    class CourseCreateArgs(val title : String, val thumbnail : Uri, val description : String, val tagList : List<String>, val onlyShow : Boolean = false,val course : Course?) : NavigationArgs() {
        companion object {
            const val COURSE_CREATE_ARG_COURSE="COURSE_CREATE_ARG_COURSE"
            const val COURSE_CREATE_ARG_ONLY_SHOW="COURSE_CREATE_ARG_ONLY_SHOW"

            const val COURSE_CREATE_ARG_TITLE = "COURSE_CREATE_ARG_TITLE"
            const val COURSE_CREATE_ARG_THUMBNAIL = "COURSE_CREATE_ARG_THUMBNAIL"
            const val COURSE_CREATE_ARG_DESCRIPTION = "COURSE_CREATE_ARG_DESCRIPTION"
            const val COURSE_CREATE_ARG_TAGLIST = "COURSE_CREATE_ARG_TAGLIST"
        }
    }

    class CourseCreateSearchArg() : NavigationArgs() {

    }

    class CourseIntroArg(val courseId: Int) : NavigationArgs() {
        companion object {
            const val COURSE_INTRO_ARG_COURSE_ID = "COURSE_INTRO_ARG_COURSE_ID"
        }
    }

    class ShowAllCourseArg : NavigationArgs()

    class ShowAllReviewArg(val isCourseReview: Boolean) : NavigationArgs() {
        companion object {
            const val SHOW_ALL_REVIEW_ARG_IS_COURSE_REVIEW = "SHOW_ALL_REVIEW_ARG_IS_COURSE_REVIEW"
        }
    }
}

//계속 추가 요망
/**
 * 액티비티에서 intent를 파싱할 때 쓰셈
 */
fun MapActivity.parseIntent(intent: Intent) = NavigationArgs.MapActivityArg(intent.getParcelableExtra<Course?>(NavigationArgs.MapActivityArg.MAP_ARG_COURSE))

fun CourseCreateIntroActivity.parseIntent(intent : Intent) = NavigationArgs.CourseCreateIntroAgs()
fun CourseCreateActivity.parseIntent(intent: Intent) = NavigationArgs.CourseCreateArgs(
    intent.getStringExtra(COURSE_CREATE_ARG_TITLE),
    intent.getParcelableExtra(COURSE_CREATE_ARG_THUMBNAIL) as Uri,
    intent.getStringExtra(COURSE_CREATE_ARG_DESCRIPTION),
    intent.getStringArrayListExtra(COURSE_CREATE_ARG_TAGLIST),
    intent.getBooleanExtra(NavigationArgs.CourseCreateArgs.COURSE_CREATE_ARG_ONLY_SHOW, false),
    intent.getParcelableExtra<Course>(COURSE_CREATE_ARG_COURSE)
)
fun CourseCreateSearchActivity.parseIntent(intent: Intent) = NavigationArgs.CourseCreateSearchArg()

fun CourseIntroActivity.parseIntent(intent: Intent) = NavigationArgs.CourseIntroArg(intent.getIntExtra(NavigationArgs.CourseIntroArg.COURSE_INTRO_ARG_COURSE_ID, 0))
fun ShowAllCoursesActivity.parseIntent(intent: Intent) = NavigationArgs.ShowAllCourseArg()
fun ShowAllReviewActivity.parseIntent(intent: Intent) = NavigationArgs.ShowAllReviewArg(intent.getBooleanExtra(NavigationArgs.ShowAllReviewArg.SHOW_ALL_REVIEW_ARG_IS_COURSE_REVIEW, false))


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
        is NavigationArgs.CourseCreateIntroAgs -> Intent(curActivity,CourseCreateIntroActivity::class.java)
        is NavigationArgs.CourseCreateArgs -> Intent(curActivity, CourseCreateActivity::class.java).apply {
            putExtra(COURSE_CREATE_ARG_TITLE, arg.title)
            putExtra(COURSE_CREATE_ARG_THUMBNAIL,arg.thumbnail)
            putExtra(COURSE_CREATE_ARG_DESCRIPTION,arg.description)
            putStringArrayListExtra(COURSE_CREATE_ARG_TAGLIST,ArrayList(arg.tagList))
            putExtra(COURSE_CREATE_ARG_ONLY_SHOW,arg.onlyShow)
            putExtra(COURSE_CREATE_ARG_COURSE,arg.course)
        }
        is NavigationArgs.CourseCreateSearchArg -> Intent(curActivity, CourseCreateSearchActivity::class.java)
        is NavigationArgs.CourseIntroArg -> Intent(curActivity, CourseIntroActivity::class.java).apply {
            putExtra(NavigationArgs.CourseIntroArg.COURSE_INTRO_ARG_COURSE_ID, arg.courseId)
        }
        is NavigationArgs.ShowAllCourseArg -> Intent(curActivity, ShowAllCoursesActivity::class.java)
        is NavigationArgs.ShowAllReviewArg -> Intent(curActivity, ShowAllReviewActivity::class.java).apply {
            putExtra(NavigationArgs.ShowAllReviewArg.SHOW_ALL_REVIEW_ARG_IS_COURSE_REVIEW, arg.isCourseReview)
        }
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