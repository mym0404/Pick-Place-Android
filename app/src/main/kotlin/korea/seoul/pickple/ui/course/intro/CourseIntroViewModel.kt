package korea.seoul.pickple.ui.course.intro

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.*
import korea.seoul.pickple.R
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.repository.CourseRepository
import korea.seoul.pickple.data.repository.FakeCourseRepository
import korea.seoul.pickple.data.repository.FakePlaceRepository
import korea.seoul.pickple.data.repository.ReviewRepository
import korea.seoul.pickple.ui.BaseViewModel

class CourseIntroViewModel(
    courseId: Int,
    private val courseRepository: CourseRepository,
    private val reviewRepository: ReviewRepository
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
                    },
                    failCallback = {
                        _course.value = FakeCourseRepository.fakeCourse
                    },
                    errorCallback = {
                        _course.value = FakeCourseRepository.fakeCourse
                    }
                )

            reviewRepository.getCourseReviews(courseId)
                .callback(
                    successCallback = { reviews ->
                        _courseReviews.value = reviews
                    },
                    failCallback = {  _courseReviews.value = listOf()},
                    errorCallback = {  _courseReviews.value = listOf()}
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
    * course의 후기
    * */
    private val _courseReviews: MutableLiveData<List<Review>> = MutableLiveData()
    val courseReviews: LiveData<List<Review>> = _courseReviews

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
    * 현재 선택된 Place의 후기
    * */
    private val _placeReviews: MutableLiveData<List<Review>> = MutableLiveData()
    val placeReviews: LiveData<List<Review>> = _placeReviews

    /**
     * 현재 선택한 Place의 index 1부터 시작한다.
     * */
    private val _index: MutableLiveData<Int> = MutableLiveData()
    val index: LiveData<String> = Transformations.map(_index) {
        if (it>10) it.toString() else "0$it"
    } // 한 자리 index는 0을 붙혀서 보여준다.

    /**
    * 후기의 Emotion
    * */
    private val _currentEmotion: MutableLiveData<Review.Emotion> = MutableLiveData()
    val currentEmotion: LiveData<Review.Emotion> = _currentEmotion

    /**
     * ViewModel이 처음 생성되었을때 세팅해줄 것
     * */
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

        places.managedObserve {

        }

        _currentEmotion.value = Review.Emotion.EMOTION1
    }

    /**
     * 사용자가 이미지를 스크롤해서 특정 index의 Place를 고름
     * index 는 1-based index이다!
     * @param index 1-based index
     * */
    fun selectPlace(index: Int) {
        _index.value = index
    }
    
    /**
     * 사용자가 리뷰에 감정을 설정하였다.
     * */
    fun selectEmotion(emotion: Review.Emotion) {
        _currentEmotion.value = emotion
    }
}