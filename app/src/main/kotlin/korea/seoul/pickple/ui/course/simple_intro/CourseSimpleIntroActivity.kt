package korea.seoul.pickple.ui.course.simple_intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.loadImage
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.util.toTagList
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.repository.CourseRepository
import kotlinx.android.synthetic.main.activity_course_simple_intro.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 코스소개 - 첫화면 을 구현한 화면이다.
 * 복잡하지 않은 화면이라 ViewModel 을 생략하고, Course 정보만 단순히 그려주고 마무리하자.
 *
 * (반드시 넘겨야한다.) courseId : 우리가 선택한 코스의 아이디 정보
 * @author greedy0110
 * */
class CourseSimpleIntroActivity : AppCompatActivity() {
    private val courseRepository: CourseRepository by inject()
    private var courseId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_simple_intro)

        courseId = intent.getIntExtra("courseId", -1)

        courseRepository.getCourseWithId(courseId)
            .callback(
                successCallback = {
                    // 화면을 갱신해주자.
                    updateCourseInfo(it)
                }
            )
    }

    private fun updateCourseInfo(course: Course) {
        with(course) {
            imageBackground.loadImage(course.thumbnail)
            textCourseName.text = name
            textCourseType.text = Course.Type.display(type)
            textHashTagList.text = tagList.toTagList()
        }
    }
}
