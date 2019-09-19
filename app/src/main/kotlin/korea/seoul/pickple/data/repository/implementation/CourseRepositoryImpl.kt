package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.CourseAPI
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import retrofit2.Call

class CourseRepositoryImpl (private val courseAPI : CourseAPI) : CourseRepository {
    override fun getCourseWithId(id: Int): Call<Course> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlaces(course: Course): Call<List<Place>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllCourses(): Call<List<Course>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}