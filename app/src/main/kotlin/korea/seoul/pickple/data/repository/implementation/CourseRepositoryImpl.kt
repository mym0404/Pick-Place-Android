package korea.seoul.pickple.data.repository.implementation

import android.net.Uri
import korea.seoul.pickple.common.util.MultiPartUtil
import korea.seoul.pickple.data.api.CourseAPI
import korea.seoul.pickple.data.api.request.course.CourseLikeRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.course.CourseInfoResponse
import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import okhttp3.RequestBody
import retrofit2.Call

class CourseRepositoryImpl (private val courseAPI : CourseAPI,private val multiPartUtil: MultiPartUtil) : CourseRepository {


    override fun createCourse(name: String, description: String, thumbnail: Uri, places: List<Int>, distances: List<Float>, tags: List<String>, district: SeoulDistrict, type: Course.Type) : Call<BaseResponse> {

        val params: HashMap<String, RequestBody> = hashMapOf(
            "courseName" to name,
            "description" to description,
            "place[0]" to places.getOrNull(0),
            "place[1]" to places.getOrNull(1),
            "place[2]" to places.getOrNull(2),
            "place[3]" to places.getOrNull(3),
            "place[4]" to places.getOrNull(4),
            "distance[0]" to distances.getOrNull(0),
            "distance[1]" to distances.getOrNull(1),
            "distance[2]" to distances.getOrNull(2),
            "distance[3]" to distances.getOrNull(3),
            "tag[0]" to tags.getOrNull(0),
            "tag[1]" to tags.getOrNull(1),
            "tag[2]" to tags.getOrNull(2),
            "tag[3]" to tags.getOrNull(3),
            "tag[4]" to tags.getOrNull(4),
            "district" to district.code,
            "type" to type.type,
            "icon" to type.type
        ).filter { it.value != null }.mapValues {

            multiPartUtil.stringToPart(it.value.toString())
        } as HashMap<String,RequestBody>

        return courseAPI.createCourse(
            params,
            multiPartUtil.uriToPart("course_thumbnail",thumbnail)
        )

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