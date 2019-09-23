package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.CourseAPI
import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.fake.FakeCourseRepository
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import retrofit2.Call
import retrofit2.mock.Calls

class CourseRepositoryImpl (private val courseAPI : CourseAPI) : CourseRepository {

    override fun getCourseWithId(id: Int): Call<Course> {
        return Calls.response(FakeCourseRepository.fakeCourse)
    }

    override fun getPlaces(course: Course): Call<List<Place>> {
        return Calls.response(listOf())
    }

    override fun getAllCourses(): Call<List<Course>> {
        return Calls.response(listOf())
    }

    override fun getHashTags(tagName: String): Call<GetHashTagResponse> {
        return courseAPI.getHashTags(tagName)
    }
}