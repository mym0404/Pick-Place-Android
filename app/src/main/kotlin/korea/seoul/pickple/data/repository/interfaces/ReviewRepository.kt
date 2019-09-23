package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.review.ReviewListResponse
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.enumerator.ReviewType
import retrofit2.Call

interface ReviewRepository {
    fun enrollCourseReview(courseId : Int, comment : String, emotion: Review.Emoticon) : Call<BaseResponse>
    fun enrollPlaceReview(placeId : Int, comment : String, emoticon : Review.Emoticon) : Call<BaseResponse>
    fun listReviews(type : ReviewType) : Call<ReviewListResponse>
}