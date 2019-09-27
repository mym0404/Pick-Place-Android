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

    override fun getCourseWithId(id: Int): Call<Course> {
        return Calls.response(FakeCourseRepository.fakeCourse)
    }

    override fun getPlaces(course: Course): Call<List<Place>> {
        return Calls.response(listOf(
                        Place(
                id = 1,
                type = Place.Type.FOOD,
                name = "${listOf("명주","수민","승민","소민").random()}네 집",
                description = "${listOf("깨끗","아늑","더럽기까지","기괴","심심").random()}함",
                phoneNumber = "010-${Random.nextInt(1000,10000)}-${Random.nextInt(1000,10000)}",
                location = listOf(
                    Location(37.6371,127.0247, null),
                    Location(37.4766,126.9816,null),
                    Location(37.4626,126.9383, null)
                ).random(),
                price = Random.nextInt(5000,300000000),
                likeCount = 999,
                thumbnail =
                    listOf(
                        "https://previews.123rf.com/images/beholdereye/beholdereye1305/beholdereye130500006/19454749-sound-waves-oscillating-on-black-background-vector-file-included.jpg",
                        "https://cdn.thewirecutter.com/wp-content/uploads/2018/06/unexpectedpetaccessories-Sabrina-lowres-.jpg",
                        "https://cdn.thewirecutter.com/wp-content/uploads/2018/06/unexpectedpetaccessories-Tim-B-lowres-.jpg",
                        "https://i.ytimg.com/vi/MBtJdkiEhBk/maxresdefault.jpg"
                    ).random()
            )
        )
        )
    }

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