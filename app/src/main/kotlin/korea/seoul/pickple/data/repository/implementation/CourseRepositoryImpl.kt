package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.CourseAPI
import korea.seoul.pickple.data.api.request.course.CourseLikeRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.course.CourseInfoResponse
import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.fake.FakeCourseRepository
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import retrofit2.Call
import retrofit2.mock.Calls
import kotlin.random.Random

class CourseRepositoryImpl (private val courseAPI : CourseAPI) : CourseRepository {


    override fun getAllCourses(): Call<List<Course>> {
        return Calls.response(listOf())
    }

    override fun getHashTags(tagName: String): Call<GetHashTagResponse> {
        return courseAPI.getHashTags(tagName)
    }

    override fun getCourseInfo(idx: Int): Call<CourseInfoResponse> {
        return courseAPI.getCourseInfo(idx)
    }

    override fun likeCourse(idx: Int): Call<BaseResponse> {
        return courseAPI.likeCourse(
            CourseLikeRequest(idx)
        )
    }

    override fun unlikeCourse(idx: Int): Call<BaseResponse> {
        return courseAPI.unlikeCourse(idx)
    }
}