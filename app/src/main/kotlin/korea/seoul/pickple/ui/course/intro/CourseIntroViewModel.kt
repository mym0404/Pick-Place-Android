package korea.seoul.pickple.ui.course.intro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.util.debugE
import korea.seoul.pickple.common.util.toTagList
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.enumerator.ReviewType
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import korea.seoul.pickple.data.repository.interfaces.PlaceRepository
import korea.seoul.pickple.data.repository.interfaces.ReviewRepository
import korea.seoul.pickple.ui.BaseViewModel
import kotlin.concurrent.thread

class CourseIntroViewModel(
    private val courseRepository: CourseRepository,
    private val placeRepository: PlaceRepository,
    private val reviewRepository: ReviewRepository
) : BaseViewModel() {

    private val TAG = CourseIntroViewModel::class.java.simpleName

    /**
     * 현재 보여줄 courseId
     * */
    var courseId: Int = 0
        set(value) {
            Log.d("seungmin", "set course id $value")
            courseRepository.getCourseInfo(value)
                .callback(
                    successCallback = { course ->
                        // 코스를 좋아요 한지 여부를 확인
                        Log.e("seungmin", "get course info : $course")
                        _course.value = course.data?.toEntity() ?: return@callback
                        courseLikeChecked.value = _course.value?.isLiked?:false
                    },
                    failCallback = {
//                        _course.value = FakeCourseRepository.fakeCourse
                    },
                    errorCallback = {
                        debugE(TAG,it)
//                        _course.value = FakeCourseRepository.fakeCourse
                    }
                )

//            courseRepository.getCourseWithId(value)
//                .callback(
//                    successCallback = { course ->
//                        _course.value = course
//                    },
//                    failCallback = {
//                        _course.value = FakeCourseRepository.fakeCourse
//                    },
//                    errorCallback = {
//                        _course.value = FakeCourseRepository.fakeCourse
//                    }
//                )



            updateReview(ReviewType.COURSE, value)

            field = value
        }

    /**
    * course intro 에서 소개할 course 정보
    * */
    private val _course: MutableLiveData<Course> = MutableLiveData()
    val course: LiveData<Course> = _course

    /**
     * 현 course의 tag list
     * */
    val courseTagList: LiveData<String> = Transformations.map(course) {
        it.tagList?.toTagList()
    }

    /**
     * 현 course의 tag list 하나의 태그만 보여주자.
     * */
    val courseOnlyOneTag: LiveData<String> = Transformations.map(course) {
        it.tagList?.toTagList(1)
    }

    /**
    * 현 course의 장소 갯수 문자열
    * */
    val coursePlaceCount: LiveData<String> = Transformations.map(course) {
        "장소 ${it.places?.size}곳"
    }

    /**
     * course like check되어있는 여부
     * */
    var courseLikeChecked: MutableLiveData<Boolean> = MutableLiveData()

    /**
    * 현 course의 소요 시간 문자열
    * */
    val courseConsumeTime: LiveData<String> = Transformations.map(course) {
        it.totalHours
    }

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
            addSource(places) {
                try {
                    currentPlace.value = it[0]
                }catch(t: Throwable) {

                }
            }
            addSource(index) {
                try {
                    currentPlace.value = _places.value?.get(it - 1)
                }catch(t: Throwable) {
                    Log.d("seungmin", "set current place error $t")
                }
            }
        }
    }

    /**
    * 현재 선택된 Place의 후기
    * */
    private val _placeReviews: MutableLiveData<List<Review>> = MutableLiveData()
    val placeReviews: LiveData<List<Review>> = _placeReviews

    /**
     * 현재 선택한 Place의 indexString 1부터 시작한다.
     * */
    private val _index: MutableLiveData<Int> = MutableLiveData()
    val index: LiveData<Int> = _index
    val indexString: LiveData<String> = Transformations.map(_index) {
        if (it>10) it.toString() else "0$it"
    } // 한 자리 index는 0을 붙혀서 보여준다.
    
    /**
    * < 현재 indexString / 전체 사이즈 > 를 나타네는 문자열
    * */
    val indexNavString: LiveData<String> = Transformations.map(_index) {
        "< $it / ${places.value?.size?:0} >"
    }

    /**
    * 현재 선택한 Place의 좋아요 여부
    * */
    val currentPlaceLiked: MutableLiveData<Boolean> = MutableLiveData()

    /**
    * 후기의 Emoticon
    * */
    private val _currentEmotion: MutableLiveData<Review.Emoticon> = MutableLiveData()
    val currentEmotion: LiveData<Review.Emoticon> = _currentEmotion

    /**
     * ViewModel이 처음 생성되었을때 세팅해줄 것
     * */
    init {
        course.managedObserve {

            // 코스에 해당하는 장소 리스트를 순차적으로 받아옴
            it.places?.let { placesIdx ->
                if (placesIdx.isEmpty()) return@let

                // place가 세팅될때, index를 초기화 해야한다.
                _index.value = 1

                thread {

                    try {
                        val places = mutableListOf<Place>()
                        placesIdx.forEach { placeId ->
                            placeRepository.getPlace(placeId).execute().body()?.placeData?.toEntity()?.let {
                                Log.d("seungmin", "sync place : $placeId , $it")
                                places += it.copy(id = placeId)
                            }
                        }
                        _places.postValue(places)
                        debugE(TAG,places)
                    }catch(t : Throwable) {
                        debugE("seungmin","place load failed $t")
                    }
                }
            }
        }

        // 현재 장소에 맞는 리뷰를 갱신해줌
        // TODO 매번 비동기 통신을 하기보다, 캐싱 해놓으면 좋을 것 같아.
        currentPlace.managedObserve {
            if (it == null) return@managedObserve

            it?.also { place ->
                updateReview(ReviewType.PLACE, place.id)
            }

            // 장소에 맞게 좋아요 수정!
            currentPlaceLiked.value = it.isLiked
        }

        _currentEmotion.value = Review.Emoticon.EMOTION1

        courseLikeChecked.managedObserve { liked ->
            course.value?.let { course ->
                if (liked) {
                    courseRepository.likeCourse(courseId)
                        .callback({
                            if (it.success) {
                                Log.d("seungmin", "like course : $courseId")
                                _course.value = course.apply {
                                    isLiked = true
                                    likeCount++
                                }
                            } else {
                                Log.d("seungmin", "like course fail : $courseId")
//                                _course.value = course // 실패시 초기화
                            }
                        })
                } else {
                    courseRepository.unlikeCourse(courseId)
                        .callback({
                            if (it.success) {
                                Log.d("seungmin", "unlike course : $courseId")
                                _course.value = course.apply {
                                    isLiked = false
                                    likeCount--
                                }
                            } else {
                                Log.d("seungmin", "unlike course fail : $courseId")
//                                _course.value = course // 실패시 초기화
                            }
                        })

                }
            }
        }

        currentPlaceLiked.managedObserve { liked ->
            currentPlace.value?.let { place ->
                if (liked) {
                    placeRepository.likePlace(place.id)
                        .callback({
                            if (it.success) {
                                currentPlace.value = place.apply {
                                    isLiked = true
                                    likeCount++
                                }
                            }
                            else {

                            }
                        })
                }
                else {
                    placeRepository.unlikePlace(place.id)
                        .callback({
                            if (it.success) {
                                currentPlace.value = place.apply {
                                    isLiked = false
                                    likeCount--
                                }
                            }
                            else {

                            }
                        })
                }
            }
        }

        
    }

    /**
     * 사용자가 이미지를 스크롤해서 특정 index의 Place를 고름
     * indexString 는 1-based index이다!
     * @param index 1-based indexString
     * */
    fun selectPlace(index: Int) {
        _index.value = index
    }
    
    /**
     * 사용자가 리뷰에 감정을 설정하였다.
     * */
    fun selectEmotion(emotion: Review.Emoticon) {
        _currentEmotion.value = emotion
    }

    /**
     * place 리뷰를 등록한다.
     * */
    fun enrollPlaceReview(comment: String) {
        reviewRepository.enrollPlaceReview(
            currentPlace.value?.id?: return , comment, currentEmotion.value?:Review.Emoticon.EMOTION1
        ).callback({
            updateReview(ReviewType.PLACE, currentPlace.value?.id?: return@callback) // 리뷰등록에 성공했으면 갱신해준다.
        })
    }

    /**
     * course 리뷰를 등록한다.
     * */
    fun enrollCourseReview(comment: String) {
        reviewRepository.enrollCourseReview(
            courseId, comment, currentEmotion.value?:Review.Emoticon.EMOTION1
        ).callback({
            updateReview(ReviewType.COURSE, courseId) // 리뷰등록에 성공했으면 갱신해준다.
        })
    }

    private fun updateReview(type: ReviewType, Id: Int) {
        val updateReviewTarget = if (type == ReviewType.COURSE) _courseReviews else _placeReviews

        reviewRepository.listReviews(type)
            .callback({
                Log.d("seungmin", "update review ${type.name} $Id \n ${it.toEntityWithIdx(Id)}")
                updateReviewTarget.value = it.toEntityWithIdx(Id)
            }, {
                Log.d("seungmin", "fail")
                updateReviewTarget.value = listOf()
            }, {
                Log.d("seungmin", it.message)
                updateReviewTarget.value = listOf()
            })
    }

}