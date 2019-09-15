package korea.seoul.pickple.ui.course.intro

import androidx.lifecycle.*
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.CourseRepository
import korea.seoul.pickple.ui.BaseViewModel

class CourseIntroViewModel(
    courseId: Int,
    private val courseRepository: CourseRepository
) : BaseViewModel() {
    /**
     * 현재 보여줄 courseId
     * */
    var courseId: Int = courseId
        set(value) {
            courseRepository.getCourseWithId(courseId)
                .callback(
                    successCallback = { course ->
                        _course.value = course
                    }
                )
            field = value
        }

    /**
    * course intro 에서 소개할 course 정보
    * */
    private val _course: MutableLiveData<Course> = MutableLiveData()
    val course: LiveData<Course> = _course
    
    /**
    * course intro 에서 소개할 course 에 해당하는 place list 정보
    * */
    private val _places: MutableLiveData<List<Place>> = MutableLiveData()
    val places: LiveData<List<Place>> = _places

    /**
     * place 이미지 url 리스트
     * */
    val placeImageUrls: LiveData<List<String>> = Transformations.map(places) {
        it.map { place -> place.thumbnail }
    }

    /**
     * 현재 선택된 Place
     * */
    val currentPlace: MediatorLiveData<Place> by lazy {
        MediatorLiveData<Place>().apply {
            addSource(_places) {
                currentPlace.value = it[0]
            }
            addSource(_index) {
                currentPlace.value = _places.value?.get(it-1)
            }
        }
    }

    /**
     * 현재 선택한 Place의 index 1부터 시작한다.
     * */
    private val _index: MutableLiveData<Int> = MutableLiveData()
    val index: LiveData<String> = Transformations.map(_index) {
        if (it>10) it.toString() else "0$it"
    } // 한 자리 index는 0을 붙혀서 보여준다.

    init {
        course.managedObserve {
            courseRepository.getPlaces(it)
                .callback(
                    successCallback = { places ->
                        // place가 세팅될때, index를 초기화 해야한다.
                        _index.value = 1
                        _places.value = places
                    }
                )
        }
    }

    /**
     * 사용자가 이미지를 스크롤해서 특정 index의 Place를 고름
     * index 는 1-based index이다!
     * @param index 1-based index
     * */
    fun selectPlace(index: Int) {
        _index.value = index
    }
}