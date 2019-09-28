package korea.seoul.pickple.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.util.debugE
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.api.MyPageAPI
import korea.seoul.pickple.data.api.OpenAPI
import korea.seoul.pickple.data.api.OpenAPIDTO
import korea.seoul.pickple.data.api.response.mypage.ListMyCoursesResponse
import korea.seoul.pickple.data.api.response.mypage.ListMyLikePlaceResponse
import korea.seoul.pickple.data.api.response.mypage.ListMyReviewResponse
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.entity.SeoulNews
import korea.seoul.pickple.data.repository.interfaces.MainRepository
import korea.seoul.pickple.data.repository.interfaces.SetRepository

/**
 * Created by mj on 22, September, 2019
 */

class NavigationViewModel(private val openAPI : OpenAPI,private val mainRepository: MainRepository, private val myPageAPI: MyPageAPI, private val setRepository: SetRepository) : ViewModel() {

    private val TAG = NavigationViewModel::class.java.simpleName

    val seoulNews: MutableLiveData<List<OpenAPIDTO.CulturalEventInfo.Row>> = MutableLiveData(
        listOf(
        )
    )
    val myCourse: MutableLiveData<List<ListMyCoursesResponse.Data.CourseDTO>> = MutableLiveData(
        listOf(


        )
    )
    val pickPlace: MutableLiveData<List<ListMyLikePlaceResponse.Data.PlaceDTO>> = MutableLiveData(
        listOf(

        )
    )
    val review: MutableLiveData<List<ListMyReviewResponse.Data.ReviewDTO>> = MutableLiveData(
        listOf(
        )
    )

    val nickName : MutableLiveData<String> = MutableLiveData()

    private val _clickSeoulNews: MutableLiveData<Once<OpenAPIDTO.CulturalEventInfo.Row>> = MutableLiveData()
    val clickSeoulNews: LiveData<Once<OpenAPIDTO.CulturalEventInfo.Row>>
        get() = _clickSeoulNews

    private val _clickCourse: MutableLiveData<Once<ListMyCoursesResponse.Data.CourseDTO>> = MutableLiveData()
    val clickCourse: LiveData<Once<ListMyCoursesResponse.Data.CourseDTO>>
        get() = _clickCourse
    
    private val _clickPlace : MutableLiveData<Once<ListMyLikePlaceResponse.Data.PlaceDTO>> = MutableLiveData()
    val clickPlace : LiveData<Once<ListMyLikePlaceResponse.Data.PlaceDTO>>
        get() = _clickPlace

    private val _clickReview: MutableLiveData<Once<Review>> = MutableLiveData()
    val clickReview: LiveData<Once<Review>>
        get() = _clickReview

    init {
        getDatas()

        setRepository.getUserInfo().callback (
            {
                nickName.value = it.data?.getOrNull(0)?.nickname + "님,\n오늘은 어디로 갈래요?"

            }, {

            }, {

            }
        )
    }

    private fun getDatas() {
        openAPI.listOpenAPIDatas().callback({
            try {
                seoulNews.value = it.culturalEventInfo.row
            }catch(t : Throwable) {

            }

        }, {

        }, {

        })

        myPageAPI.listMyCourses().callback({
            it.data?.let {
                myCourse.value = it.mapNotNull {
                    it.info?.getOrNull(0)
                } ?: listOf()
            }
        }, {

        }, {

        })

        myPageAPI.listPickPlace().callback({
            it.data?.let {
                pickPlace.value = it.map {
                    it.info.getOrNull(0)
                }.filterNotNull()
            }
        }, {

        }, {

        })


        myPageAPI.listMyReview().callback({

            review.value = it.data?.map {

                it?.info?.getOrNull(0)

            }?.filterNotNull() ?: listOf()


        }, {

        }, {

        })
    }

    fun onClickSeoulNews(news: OpenAPIDTO.CulturalEventInfo.Row) {
        _clickSeoulNews.value = Once(news)
    }

    fun onClickCourse(course: ListMyCoursesResponse.Data.CourseDTO) {
        _clickCourse.value = Once(course)
    }
    fun onClickPlace(place : ListMyLikePlaceResponse.Data.PlaceDTO) {
        _clickPlace.value = Once(place)
    }

    fun onClickRevieW(review: Review) {
        _clickReview.value = Once(review)
    }

}
