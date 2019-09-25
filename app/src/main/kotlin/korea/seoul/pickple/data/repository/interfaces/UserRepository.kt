package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.BaseResponse
import retrofit2.Call

interface UserRepository {
    fun signIn(email : String, password : String, callback : (success : Boolean, message : String) -> Unit)

    fun signUp(email : String, nickname : String, password : String) : Call<BaseResponse>

    fun findPassword() : Call<BaseResponse>
}