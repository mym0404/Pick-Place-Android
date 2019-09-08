package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import retrofit2.Call

interface CourseRepository {

    fun getCourseWithId(id : Int) : Call<Course>

    fun getPlaces(course : Course) : Call<List<Place>>
}