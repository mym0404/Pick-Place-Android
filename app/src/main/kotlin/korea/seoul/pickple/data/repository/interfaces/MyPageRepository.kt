package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.mypage.ListSeoulNewsResponse
import retrofit2.Call

interface MyPageRepository {
    fun listSeoulNews() : Call<ListSeoulNewsResponse>
}