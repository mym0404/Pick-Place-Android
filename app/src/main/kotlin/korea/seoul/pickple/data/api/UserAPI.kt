package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.request.user.LoginRequest
import korea.seoul.pickple.data.api.response.user.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("auth/signin")
    fun login(@Body request : LoginRequest) : Call<LoginResponse>


}