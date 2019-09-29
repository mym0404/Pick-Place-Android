package korea.seoul.pickple.data.repository.interfaces

import android.net.Uri
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.course.CourseInfoResponse
import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import retrofit2.Call

interface CourseRepository {

    fun createCourse(
        name: String,
        description: String,
        thumbnail: Uri,
        places: List<Int>,
        distances: List<Float>,
        tags: List<String>,
        district: SeoulDistrict,
        type: Course.Type
    ) : Call<BaseResponse>


    fun getHashTags(tagName: String): Call<GetHashTagResponse>

    fun getCourseInfo(idx: Int): Call<CourseInfoResponse>

    fun likeCourse(idx: Int): Call<BaseResponse>

    fun unlikeCourse(idx: Int): Call<BaseResponse>
}