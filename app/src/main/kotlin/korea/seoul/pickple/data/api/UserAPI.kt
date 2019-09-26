package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.request.user.SignInRequest
import korea.seoul.pickple.data.api.request.user.SignUpRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.user.SignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {
    @POST("auth/signin")
    fun signIn(@Body request : SignInRequest) : Call<SignInResponse>
    @POST("auth/signup")
    fun signUp(@Body request : SignUpRequest) : Call<BaseResponse>
    @GET("auth/finder/{email}")
    fun findPassword(@Path("email") email : String) : Call<BaseResponse>
}