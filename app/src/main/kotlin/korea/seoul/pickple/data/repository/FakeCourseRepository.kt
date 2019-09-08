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
                name = "${listOf("명주","수민","승민","소민").random()}네 집앞 ${listOf("흡연","산책","운동").random()} 거리",
                description = "설명은 필요없다",
                districtInSeoul = SeoulDistrict.getAllDistrict().random(),
                places = List(Random.nextInt(2,10)){ it }.distinct(),
                likeCount = Random.nextInt(500,2000)
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