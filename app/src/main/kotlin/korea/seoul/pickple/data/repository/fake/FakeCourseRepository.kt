package korea.seoul.pickple.data.repository.fake

import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.course.CourseInfoResponse
import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import korea.seoul.pickple.data.repository.interfaces.PlaceRepository
import retrofit2.Call
import retrofit2.mock.Calls
import kotlin.random.Random

class FakeCourseRepository(private val placeRepository: PlaceRepository) : CourseRepository {


    companion object {
        val fakeCourse = Course(
            id = 1,
            type = Course.Type.ORAEGAGE,
            name = "${listOf("수민").random()}이네 집앞 ${listOf("흡연").random()} 거리",
            description = "설명은 필요없다만 좀 긴 설명을 테스트 할 필요가 있기 때문에 주절 주절 아무말이나 적어보자. 지금은 9월 13일 추석 당일이다. 당일에 카페와서 작업하고 있는게 레전드다. 친구를 만날 수가 없다. 오티 자료는 언제 만들지, 시간 많을때 픽플좀 해놨어야 했는대라는 후회가 밀려오지만 어쩔수 없다. " +
                    "이미 지나간 일을 후회하는 것은 쓸모 없는 일이다. 그 시간에 현 상황에 가장 나은 선택지를 고르며 사람은 성장하는 법이다. 하지만 이게 내가 성장하고 있는 건지 설명이 성장하고 있는건진 정확하지 않다. 정말로 아무말이나 의식의 흐름대로 적고 있는데 누군가 이걸 보진 않을까 고민된다. 여기까지 읽었다는 말은 사실 그 사람도 집중력이 떨어져 있는 것이라고 생각한다." +
                    "나는 아니다. 나는 집중력이 떨어저서 이것을 쓰고 있는 것이 아니다. 단지 더미데이터를 만들기 위해 노력하고 있는 것이다. 믿어달라. 이건 코스 설명이다. 그냥 복붙을 해도 되지만 그냥 일기 써봤다.",
            districtInSeoul = SeoulDistrict.getAllDistrict().random(),
            places = List(3) { it }.distinct(),
            likeCount = Random.nextInt(500, 2000),
            tagList = listOf("Tag1", "Tag2", "Tag3", "Tag4"),
            thumbnail =
            listOf(
                "https://previews.123rf.com/images/beholdereye/beholdereye1305/beholdereye130500006/19454749-sound-waves-oscillating-on-black-background-vector-file-included.jpg",
                "https://cdn.thewirecutter.com/wp-content/uploads/2018/06/unexpectedpetaccessories-Sabrina-lowres-.jpg",
                "https://cdn.thewirecutter.com/wp-content/uploads/2018/06/unexpectedpetaccessories-Tim-B-lowres-.jpg",
                "https://i.ytimg.com/vi/MBtJdkiEhBk/maxresdefault.jpg"
            ).random()
        )
    }

    override fun getCourseWithId(id: Int): Call<Course> {
        return Calls.response(
            Course(
                id = id,
                type = Course.Type.ORAEGAGE,
                name = "${listOf("명주", "수민", "승민", "소민").random()}네 집앞 ${listOf("흡연", "산책", "운동").random()} 거리",
                description = "설명은 필요없다만 좀 긴 설명을 테스트 할 필요가 있기 때문에 주절 주절 아무말이나 적어보자. 지금은 9월 13일 추석 당일이다. 당일에 카페와서 작업하고 있는게 레전드다. 친구를 만날 수가 없다. 오티 자료는 언제 만들지, 시간 많을때 픽플좀 해놨어야 했는대라는 후회가 밀려오지만 어쩔수 없다. " +
                        "이미 지나간 일을 후회하는 것은 쓸모 없는 일이다. 그 시간에 현 상황에 가장 나은 선택지를 고르며 사람은 성장하는 법이다. 하지만 이게 내가 성장하고 있는 건지 설명이 성장하고 있는건진 정확하지 않다. 정말로 아무말이나 의식의 흐름대로 적고 있는데 누군가 이걸 보진 않을까 고민된다. 여기까지 읽었다는 말은 사실 그 사람도 집중력이 떨어져 있는 것이라고 생각한다." +
                        "나는 아니다. 나는 집중력이 떨어저서 이것을 쓰고 있는 것이 아니다. 단지 더미데이터를 만들기 위해 노력하고 있는 것이다. 믿어달라. 이건 코스 설명이다. 그냥 복붙을 해도 되지만 그냥 일기 써봤다.",
                districtInSeoul = SeoulDistrict.getAllDistrict().random(),
                places = List(3) { it }.distinct(),
                likeCount = Random.nextInt(500, 2000),
                tagList = listOf("Tag1", "Tag2", "Tag3", "Tag4"),
                thumbnail =
                listOf(
                    "https://previews.123rf.com/images/beholdereye/beholdereye1305/beholdereye130500006/19454749-sound-waves-oscillating-on-black-background-vector-file-included.jpg",
                    "https://cdn.thewirecutter.com/wp-content/uploads/2018/06/unexpectedpetaccessories-Sabrina-lowres-.jpg",
                    "https://cdn.thewirecutter.com/wp-content/uploads/2018/06/unexpectedpetaccessories-Tim-B-lowres-.jpg",
                    "https://i.ytimg.com/vi/MBtJdkiEhBk/maxresdefault.jpg"
                ).random()
            )
        )
    }

    override fun getPlaces(course: Course): Call<List<Place>> {
//        val places =   try {course.places.map {
//            placeRepository.getPlace(it).execute().body()!!
//        }}catch (t : Throwable){listOf<Place>()}
//
//        return Calls.response(places.map { it.placeData!!.toEntity() })
        TODO()
    }

    override fun getAllCourses(): Call<List<Course>> {
        return Calls.response(
            List(10) { fakeCourse }
        )
    }

    override fun getHashTags(tagName: String): Call<GetHashTagResponse> {
        TODO()
    }

    override fun getCourseInfo(idx: Int): Call<CourseInfoResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun likeCourse(idx: Int): Call<BaseResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unlikeCourse(idx: Int): Call<BaseResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}