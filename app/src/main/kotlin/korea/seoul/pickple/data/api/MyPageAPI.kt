package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.response.mypage.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MyPageAPI {
    @GET("mypage/seoul")
    fun listSeoulNews() : Call<ListSeoulNewsResponse>

    @GET("mypage/editCourse")
    fun listMyCourses() : Call<ListMyCoursesResponse>

    @GET("mypage/likeCourse")
    fun listMyLikeCourse() : Call<ListMyLikeCourseResponse>

    @GET("mypage/myReview")
    fun listMyReview() : Call<ListMyReviewResponse>

    @GET("mypage/likePlace")
    fun listPickPlace() : Call<ListMyLikePlaceResponse>


}