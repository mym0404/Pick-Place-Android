package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.UserAPI
import korea.seoul.pickple.data.api.request.user.SignInRequest
import korea.seoul.pickple.data.api.request.user.SignUpRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.user.SignInResponse
import korea.seoul.pickple.data.repository.interfaces.UserRepository
import retrofit2.Call

class UserRepositoryImpl(private val userAPI : UserAPI) : UserRepository {
    override fun signIn(email: String, password: String): Call<SignInResponse> {
        return userAPI.signIn(SignInRequest(email,password))
    }

    override fun signUp(email: String, nickname: String, password: String): Call<BaseResponse> {
        return userAPI.signUp(SignUpRequest(email,password,nickname))
    }
}