package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.ReviewAPI
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.repository.interfaces.ReviewRepository
import retrofit2.Call

class ReviewRepositoryImpl(private val reviewAPI: ReviewAPI) : ReviewRepository {
    override fun getCourseReviews(courseId: Int): Call<List<Review>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlaceReviews(placeId: Int): Call<List<Review>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}