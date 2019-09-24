package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.main.MainListResponse
import korea.seoul.pickple.data.api.response.review.ReviewListResponse
import korea.seoul.pickple.data.enumerator.ReviewType
import retrofit2.Call

interface MainRepository {
    fun listMainCourses(type : Int) : Call<MainListResponse>
}