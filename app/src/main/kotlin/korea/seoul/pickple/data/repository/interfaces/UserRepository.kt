package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.user.SignInResponse
import retrofit2.Call

interface UserRepository {
    fun signIn(email : String, password : String) : Call<SignInResponse>
    fun signUp(email : String, nickname : String, password : String) : Call<BaseResponse>
}