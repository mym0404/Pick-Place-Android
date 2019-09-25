package korea.seoul.pickple.ui.navigation.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.R
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.repository.interfaces.MainRepository

/**
 * Created by mj on 23, September, 2019
 */

class NavigationCourseViewModel(private val courseType : Course.Type,private val mainRepository: MainRepository)  : ViewModel() {

    private val _courses : MutableLiveData<List<Course>> = MutableLiveData()
    val courses : LiveData<List<Course>>
        get() = _courses

    val title = when(courseType) {
        Course.Type.UNKNOWN->""
        Course.Type.ORAEGAGE->"오래가게 코스"
        Course.Type.KOREA_TRADITIONAL->"한국 전통 코스"
        Course.Type.CUSTOM->"사용자 코스"
    }
    val titleImage = when(courseType) {
        Course.Type.UNKNOWN-> R.drawable.oldshop_course_icon
        Course.Type.ORAEGAGE->R.drawable.oldshop_course_icon
        Course.Type.KOREA_TRADITIONAL->R.drawable.history_course_icon
        Course.Type.CUSTOM->R.drawable.more_btn_map
    }

    init {
        getDatas()
    }

    private fun getDatas() {
        mainRepository.listMainCourses(courseType.type)
            .callback({
                _courses.value =  it.data?.map {
                    it.info?.getOrNull(0)?.toEntity()
                }?.filterNotNull() ?: listOf()
            }, {

            }, {

            })
    }

}