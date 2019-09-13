package korea.seoul.pickple.ui.course.unite_intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.util.toTagList
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.repository.CourseRepository

class UniteCourseViewModel(
    repository: CourseRepository,
    courseId: Int
) : ViewModel() {
    private val repository = repository
    var courseId = courseId
        set(value) {
            repository.getCourseWithId(value)
                .callback(
                    successCallback = {
                        _displayCourse.value = it
                    }
                )
            field = value
        }

    /**
    * 현재 Display 해야하는 Course 정보를 나타낸다.
    * */
    private val _displayCourse: MutableLiveData<Course> = MutableLiveData()
    val displayCourse: LiveData<Course> = _displayCourse

    /**
     * 현재 Course의 tag를 하나만 보여주자.
     * */
    val courseTag: LiveData<String> = Transformations.map(displayCourse) {
        it.tagList.toTagList(1)
    }

    /**
     * 현재 Course의 Display Type을 보여주자.
     * */
    val courseType: LiveData<String> = Transformations.map(displayCourse) {
        Course.Type.display(it.type)
    }
}