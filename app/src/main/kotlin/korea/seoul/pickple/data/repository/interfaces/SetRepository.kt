package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.set.GetUserInfoResponse
import retrofit2.Call

interface SetRepository {
    fun getUserInfo() : Call<GetUserInfoResponse>
    fun changePassword(curPassword : String, newPassword : String) : Call<BaseResponse>
    fun changeNickname(newNickname:String) : Call<BaseResponse>
    fun emailContact(comment : String) : Call<BaseResponse>
}