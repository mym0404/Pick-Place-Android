package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.SetAPI
import korea.seoul.pickple.data.api.request.set.ChangeNicknameRequest
import korea.seoul.pickple.data.api.request.set.ChangePasswordRequest
import korea.seoul.pickple.data.api.request.set.EmailContactRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.set.GetUserInfoResponse
import korea.seoul.pickple.data.repository.interfaces.SetRepository
import retrofit2.Call

class SetRepositoryImpl(private val setAPI: SetAPI) : SetRepository {
    override fun getUserInfo(): Call<GetUserInfoResponse> {
        return setAPI.getUserInfo()
    }

    override fun changePassword(curPassword: String, newPassword: String): Call<BaseResponse> {
        return setAPI.changePassword(
            ChangePasswordRequest(curPassword,newPassword)
        )
    }

    override fun changeNickname(newNickname: String): Call<BaseResponse> {
        return setAPI.changeNickname(
            ChangeNicknameRequest(newNickname)
        )
    }

    override fun emailContact(comment: String): Call<BaseResponse> {
        return setAPI.emailContact(
            EmailContactRequest(comment)
        )
    }
}