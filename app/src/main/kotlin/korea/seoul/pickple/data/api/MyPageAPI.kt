package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.response.mypage.ListSeoulNewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyPageAPI {
    @GET("mypage/seoul")
    fun listSeoulNews() : Call<ListSeoulNewsResponse>
}