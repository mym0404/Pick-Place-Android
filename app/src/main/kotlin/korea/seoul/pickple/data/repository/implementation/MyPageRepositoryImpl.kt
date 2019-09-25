package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.MyPageAPI
import korea.seoul.pickple.data.api.response.mypage.*
import korea.seoul.pickple.data.repository.interfaces.MyPageRepository
import retrofit2.Call

class MyPageRepositoryImpl(private val myPageAPI: MyPageAPI) : MyPageRepository {
    override fun listSeoulNews(): Call<ListSeoulNewsResponse> {
        return myPageAPI.listSeoulNews()
    }

    override fun listMyCourses(): Call<ListMyCoursesResponse> {
        return myPageAPI.listMyCourses()
    }

    override fun listMyLikeCourse(): Call<ListMyLikeCourseResponse> {
        return myPageAPI.listMyLikeCourse()
    }

    override fun listMyReview(): Call<ListMyReviewResponse> {
        return myPageAPI.listMyReview()
    }

    override fun listMylikePlace(): Call<ListMyLikePlaceResponse> {
        return myPageAPI.listPickPlace()
    }
}