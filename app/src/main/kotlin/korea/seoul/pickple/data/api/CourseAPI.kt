package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.response.course.GetHashTagResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseAPI {
    @GET("course/tagSearch")
    fun getHashTags(@Path("tagName") keyword : String ) : Call<GetHashTagResponse>
}