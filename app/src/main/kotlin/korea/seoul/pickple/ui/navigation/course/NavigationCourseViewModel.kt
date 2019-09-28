package korea.seoul.pickple.ui.navigation.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.api.MyPageAPI
import korea.seoul.pickple.data.api.response.mypage.ListMyCoursesResponse

/**
 * Created by mj on 23, September, 2019
 */

class NavigationCourseViewModel(private val myPageAPI: MyPageAPI)  : ViewModel() {

    private val _courses : MutableLiveData<List<ListMyCoursesResponse.Data.CourseDTO>> = MutableLiveData(listOf())
    val courses : LiveData<List<ListMyCoursesResponse.Data.CourseDTO>>
        get() = _courses

    val courseCountText : LiveData<String> = Transformations.map(courses) {
        it.size.toString() ?: "0"
    }


    private val _clickCourse : MutableLiveData<Once<ListMyCoursesResponse.Data.CourseDTO>> = MutableLiveData()
    val clickCourse : LiveData<Once<ListMyCoursesResponse.Data.CourseDTO>>
        get() = _clickCourse

    init {
        getDatas()
    }

    fun onClickCourse(item : ListMyCoursesResponse.Data.CourseDTO) {
        _clickCourse.value = Once(item)
    }
    private fun getDatas() {
        myPageAPI.listMyCourses().callback({
            it.data?.let {
                _courses.value = it.mapNotNull {
                    it.info?.getOrNull(0)
                } ?: listOf()
            }
        }, {

        }, {

        })
    }

}