package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.MyPageAPI
import korea.seoul.pickple.data.api.response.mypage.ListSeoulNewsResponse
import korea.seoul.pickple.data.repository.interfaces.MyPageRepository
import retrofit2.Call

class MyPageRepositoryImpl(private val myPageAPI: MyPageAPI) : MyPageRepository {
    override fun listSeoulNews(): Call<ListSeoulNewsResponse> {
        return myPageAPI.listSeoulNews()
    }
}