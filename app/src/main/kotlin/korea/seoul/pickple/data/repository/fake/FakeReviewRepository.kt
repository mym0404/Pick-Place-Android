package korea.seoul.pickple.data.repository.fake

import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.repository.interfaces.ReviewRepository
import retrofit2.Call
import retrofit2.mock.Calls

class FakeReviewRepository : ReviewRepository {
    companion object {
        val fakeReview: Review
            get() = Review(
                id = 1,
                comment = listOf(
                    "어쩌고저쩌고 후기내용 후기내용 후기내용 어쩌고 저쩌고 후기내용 후기내용 후기내용",
                    "랜덤한 후기중에 하나입니다요. 껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄껄",
                    "픽플은 마감이 며찰 안남은 프로젝트이다, 우리는 과연 다 마칠 수 있는가...?",
                    "SOPT 안드로이드 세미나 만들어야하는데, 만들기 귀찮다. OT 때는 무슨 말을 해야할까?"
                ).random(),
                commenter = listOf(
                    "승민이", "수민이", "명주이", "소민이", "볼드모트"
                ).random(),
                emotion = Review.Emotion.EMOTION1
            )
    }

    override fun getCourseReviews(courseId: Int): Call<List<Review>> {
        return Calls.response(
            List(10) { fakeReview }
        )
    }

    override fun getPlaceReviews(placeId: Int): Call<List<Review>> {
        return Calls.response(
            List(10) { fakeReview.copy(commenter = "따땅리뷰") }
        )
    }
}