package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.request.course.CourseLikeRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.course.CourseInfoResponse
import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface CourseAPI {

    @GET("course/info/{id}")
    fun getCourseInfo(@Path("id") courseId: Int): Call<CourseInfoResponse>

    @POST("course/like")
    fun likeCourse(@Body request: CourseLikeRequest): Call<BaseResponse>

    @DELETE("course/like/{id}")
    fun unlikeCourse(@Path("id") courseIdx: Int): Call<BaseResponse>


    @GET("course/tagSearch")
    fun getHashTags(@Path("tagName") keyword: String): Call<GetHashTagResponse>

    @POST("course/edit")
    @Multipart
    fun createCourse(
        @Part("courseName") name: String,
        @Part("description") description: String,
        @Part thumbnail: MultipartBody.Part,
        @Part("place[0]") place1: Int,
        @Part("place[1]") place2: Int,
        @Part("place[2]") place3: Int,
        @Part("place[3]") place4: Int,
        @Part("place[4]") place5: Int,
        @Part("distance[0]") distance12: Float,
        @Part("distance[1]") distance23: Float,
        @Part("distance[2]") distance34: Float,
        @Part("distance[3]") distance45: Float,
        @Part("tag[0]") tag1: String,
        @Part("tag[1]") tag2: String,
        @Part("tag[2]") tag3: String,
        @Part("tag[3]") tag4: String,
        @Part("tag[4]") tag5: String,
        @Part("date") date: String,
        @Part("district") district: Int,
        @Part("type") type: Int,
        @Part("icon") icon: Int,
        @Part("totalHour") totalHour: String
    ) : Call<BaseResponse>




}