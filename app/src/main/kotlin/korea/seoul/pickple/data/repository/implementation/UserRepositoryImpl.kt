package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.common.util.TokenUtil
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.util.debugE
import korea.seoul.pickple.data.api.UserAPI
import korea.seoul.pickple.data.api.request.user.SignInRequest
import korea.seoul.pickple.data.api.request.user.SignUpRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.repository.interfaces.UserRepository
import retrofit2.Call

class UserRepositoryImpl(private val userAPI : UserAPI,private val tokenUtil : TokenUtil) : UserRepository {
    override fun signIn(email: String, password: String, callback: (success: Boolean, message: String) -> Unit) {
        userAPI.signIn(SignInRequest(email,password))
            .callback({
                if(it.success) {
                    it.tokenDatas?.run {
                        debugE("TAG","save Token!")
                        tokenUtil.saveToken(this.token)
                    }
                    callback(true,it.message)
                }else {
                    callback(false,it.message)
                }
            }, {
                callback(false,"error")
            }, {
                callback(false,"error")
            })
    }


    override fun signUp(email: String, nickname: String, password: String): Call<BaseResponse> {
        return userAPI.signUp(SignUpRequest(email,password,nickname))
    }

    override fun findPassword(): Call<BaseResponse> {
        return userAPI.findPassword()
    }
}