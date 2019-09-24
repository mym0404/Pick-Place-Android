package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.MainAPI
import korea.seoul.pickple.data.api.response.main.MainListResponse
import korea.seoul.pickple.data.repository.interfaces.MainRepository
import retrofit2.Call

class MainRepositoryImpl(private val mainAPI : MainAPI) : MainRepository {
    override fun listMainCourses(type: Int): Call<MainListResponse> {
        return mainAPI.listMainCourses(type)
    }

    override fun listMainCoursesWithPopularOrder(): Call<MainListResponse> {
        return mainAPI.listMainCoursesWithPopularOrder()
    }
}