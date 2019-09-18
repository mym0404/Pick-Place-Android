package korea.seoul.pickple.ui.course.intro.all_course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.repository.CourseRepository
import korea.seoul.pickple.ui.BaseViewModel

class ShowAllCoursesViewModel(
    courseRepository: CourseRepository
) : BaseViewModel() {

    /**
    * 모든 course 데이터
    * */
    private val _courses: MutableLiveData<List<Course>> = MutableLiveData()
    val courses: LiveData<List<Course>> = _courses

    init {
        courseRepository.getAllCourses()
            .callback(
                successCallback = {
                    _courses.value = it
                },
                failCallback = {
                    _courses.value = listOf()
                },
                errorCallback =  {
                    _courses.value = listOf()
                }
            )
    }
}