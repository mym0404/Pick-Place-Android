package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.mypage.ListMyCoursesResponse
import korea.seoul.pickple.data.api.response.mypage.ListMyLikeCourseResponse
import korea.seoul.pickple.data.api.response.mypage.ListMyLikePlaceResponse
import korea.seoul.pickple.data.api.response.mypage.ListSeoulNewsResponse
import retrofit2.Call

interface MyPageRepository {
    fun listSeoulNews() : Call<ListSeoulNewsResponse>

    fun listMyCourses() : Call<ListMyCoursesResponse>

    fun listMyLikeCourse() : Call<ListMyLikeCourseResponse>

    fun listMylikePlace() : Call<ListMyLikePlaceResponse>
}