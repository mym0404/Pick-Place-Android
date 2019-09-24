package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.request.course.CourseLikeRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.course.CourseInfoResponse
import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import retrofit2.Call
import retrofit2.http.*

interface CourseAPI {

    @GET("course/info/{id}")
    fun getCourseInfo(@Path("id") courseId : Int) : Call<CourseInfoResponse>

    @POST("course/like")
    fun likeCourse(@Body request : CourseLikeRequest) : Call<BaseResponse>

    @DELETE("course/like/{id}")
    fun unlikeCourse(@Path("id") courseIdx : Int) : Call<BaseResponse>



    @GET("course/tagSearch")
    fun getHashTags(@Path("tagName") keyword : String ) : Call<GetHashTagResponse>


}