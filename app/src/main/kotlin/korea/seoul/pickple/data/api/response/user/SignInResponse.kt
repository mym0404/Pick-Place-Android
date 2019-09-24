package korea.seoul.pickple.data.api.response.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import korea.seoul.pickple.data.api.dto.TokenDTO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInResponse(
    @Expose
    var status : Int,
    @Expose
    var success : Boolean,
    @Expose
    var message : String,
    @Expose
    var tokenDatas : TokenDTO?
) : Parcelable