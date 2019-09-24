package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.response.mypage.ListMyCoursesResponse
import korea.seoul.pickple.data.api.response.mypage.ListMyLikeCourseResponse
import korea.seoul.pickple.data.api.response.mypage.ListMyLikePlaceResponse
import korea.seoul.pickple.data.api.response.mypage.ListSeoulNewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyPageAPI {
    @GET("mypage/seoul")
    fun listSeoulNews() : Call<ListSeoulNewsResponse>

    @GET("mypage/editCourse")
    fun listMyCourses() : Call<ListMyCoursesResponse>

    @GET("mypage/likeCourse")
    fun listMyLikeCourse() : Call<ListMyLikeCourseResponse>

    @GET("mypage/likePlace")
    fun listMyLikePlace() : Call<ListMyLikePlaceResponse>

}