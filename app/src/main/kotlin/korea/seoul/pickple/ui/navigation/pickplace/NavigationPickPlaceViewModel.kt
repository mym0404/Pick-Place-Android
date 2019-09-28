package korea.seoul.pickple.ui.navigation.pickplace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.api.dto.CourseDTO
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.repository.interfaces.MainRepository
import korea.seoul.pickple.data.repository.interfaces.MyPageRepository

/**
 * Created by mj on 27, September, 2019
 */

class NavigationPickPlaceViewModel(private val mainRepository: MainRepository,private val myPageRepository: MyPageRepository) : ViewModel() {

    private val TAG = NavigationPickPlaceViewModel::class.java.simpleName

    private val _oragageCourses : MutableLiveData<List<CourseDTO>> = MutableLiveData(listOf())
    val oragageCourses : LiveData<List<CourseDTO>>
        get() = _oragageCourses
    
    private val _traditionalCourses : MutableLiveData<List<CourseDTO>> = MutableLiveData(listOf())
    val traditionalCourses : LiveData<List<CourseDTO>>
        get() = _traditionalCourses
    
    private val _myCourses : MutableLiveData<List<CourseDTO>> = MutableLiveData(listOf())
    val myCourses : LiveData<List<CourseDTO>>
        get() = _myCourses

    private val _clickCourse : MutableLiveData<Once<CourseDTO>> = MutableLiveData()
    val clickCourse : LiveData<Once<CourseDTO>>
        get() = _clickCourse

    init {
        setDatas()
    }

    fun onClickCourse(course : CourseDTO) {
        _clickCourse.value = Once(course)
    }

    private fun setDatas() {
        mainRepository.listMainCourses(Course.Type.ORAEGAGE.type)
            .callback({
                _oragageCourses.value = it.data?.let {data->
                    data?.map { it.info.getOrNull(0) }
                }?.filterNotNull() ?: listOf()
            }, {

            }, {

            })
        mainRepository.listMainCourses(Course.Type.KOREA_TRADITIONAL.type)
            .callback({
                _traditionalCourses.value = it.data?.let {data->
                    data?.map { it.info.getOrNull(0) }
                }?.filterNotNull() ?: listOf()
            }, {

            }, {

            })
        mainRepository.listMainCourses(Course.Type.CUSTOM.type)
            .callback({
                _myCourses.value = it.data?.let {data->
                    data?.map { it.info.getOrNull(0) }
                }?.filterNotNull() ?: listOf()
            }, {

            }, {

            })

    }
}