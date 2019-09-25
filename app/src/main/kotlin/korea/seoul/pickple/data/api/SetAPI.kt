package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.request.set.ChangeNicknameRequest
import korea.seoul.pickple.data.api.request.set.ChangePasswordRequest
import korea.seoul.pickple.data.api.request.set.EmailContactRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.set.GetUserInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface SetAPI {
    @GET("set/info")
    fun getUserInfo() : Call<GetUserInfoResponse>

    @PUT("set/modify/password")
    fun changePassword(@Body request : ChangePasswordRequest) : Call<BaseResponse>

    @PUT("set/modify/nickname")
    fun changeNickname(@Body request : ChangeNicknameRequest) : Call<BaseResponse>

    @POST("set/contact")
    fun emailContact(@Body request : EmailContactRequest) : Call<BaseResponse>
}