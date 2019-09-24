package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.mypage.*
import retrofit2.Call

interface MyPageRepository {
    fun listSeoulNews() : Call<ListSeoulNewsResponse>

    fun listMyCourses() : Call<ListMyCoursesResponse>

    fun listMyLikeCourse() : Call<ListMyLikeCourseResponse>

    fun listMyReview() : Call<ListMyReviewResponse>

    fun listMylikePlace() : Call<ListMyLikePlaceResponse>
}