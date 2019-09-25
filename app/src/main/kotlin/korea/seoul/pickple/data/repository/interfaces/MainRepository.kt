package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.main.MainListResponse
import retrofit2.Call

interface MainRepository {
    fun listMainCourses(type : Int) : Call<MainListResponse>

    fun listMainCoursesWithPopularOrder() : Call<MainListResponse>
}