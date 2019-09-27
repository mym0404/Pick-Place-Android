package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.request.review.EnrollCourseReviewRequest
import korea.seoul.pickple.data.api.request.review.EnrollPlaceReviewRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.review.ReviewListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ReviewAPI {
    @POST("review/enroll/course")
    fun enrollCourseReview(@Body request : EnrollCourseReviewRequest) : Call<BaseResponse>

    @POST("review/enroll/place")
    fun enrollPlaceReview(@Body request : EnrollPlaceReviewRequest) : Call<BaseResponse>

    @GET("review/list/{type}")
    fun listReviews(@Path("type") type : Int) : Call<ReviewListResponse>

}

