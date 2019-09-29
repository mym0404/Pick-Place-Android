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
        @PartMap() params : HashMap<String,Any>,
        @Part thumbnail: MultipartBody.Part
    ) : Call<BaseResponse>




}