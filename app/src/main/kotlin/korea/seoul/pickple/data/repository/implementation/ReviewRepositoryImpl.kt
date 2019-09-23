package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.ReviewAPI
import korea.seoul.pickple.data.api.request.review.EnrollCourseReviewRequest
import korea.seoul.pickple.data.api.request.review.EnrollPlaceReviewRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.review.ReviewListResponse
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.enumerator.ReviewType
import korea.seoul.pickple.data.repository.interfaces.ReviewRepository
import retrofit2.Call

class ReviewRepositoryImpl(private val reviewAPI: ReviewAPI) : ReviewRepository {
    override fun enrollCourseReview(courseId: Int, comment: String, emotion: Review.Emoticon): Call<BaseResponse> {
        return reviewAPI.enrollCourseReview(
            EnrollCourseReviewRequest(courseId,comment,emotion.value)
        )
    }

    override fun enrollPlaceReview(placeId: Int, comment: String, emoticon: Review.Emoticon): Call<BaseResponse> {
        return reviewAPI.enrollPlaceReview(
            EnrollPlaceReviewRequest(placeId,comment,emoticon.value)
        )
    }

    override fun listReviews(type: ReviewType): Call<ReviewListResponse> {
        return reviewAPI.listReviews(type.value)
    }
}