package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.entity.Review
import retrofit2.Call

interface ReviewRepository {
    fun getCourseReviews(courseId: Int): Call<List<Review>>

    fun getPlaceReviews(placeId: Int) : Call<List<Review>>
}