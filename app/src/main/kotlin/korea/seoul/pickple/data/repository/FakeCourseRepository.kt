package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import retrofit2.Call
import retrofit2.mock.Calls
import kotlin.random.Random

class FakeCourseRepository(private val placeRepository: PlaceRepository) : CourseRepository {
    override fun getCourseWithId(id: Int): Call<Course> {
        return Calls.response(
            Course(
                id = id,
                type = Course.Type.ORAEGAGE,
                name = "${listOf("명주","수민","승민","소민").random()}네 집앞 ${listOf("흡연","산책","운동").random()} 거리",
                description = "설명은 필요없다",
                districtInSeoul = SeoulDistrict.getAllDistrict().random(),
                places = List(3){ it }.distinct(),
                likeCount = Random.nextInt(500,2000),
                tagList = listOf("Tag1","Tag2","Tag3","Tag4"),
                thumbnail = "https://previews.123rf.com/images/beholdereye/beholdereye1305/beholdereye130500006/19454749-sound-waves-oscillating-on-black-background-vector-file-included.jpg"
            )
        )
    }

    override fun getPlaces(course : Course): Call<List<Place>> {
        val places = course.places.map {
            placeRepository.getPlaceWithId(it).execute().body()!!
        }
        return Calls.response(places)
    }
}