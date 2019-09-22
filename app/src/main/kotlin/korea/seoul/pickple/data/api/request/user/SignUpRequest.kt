package korea.seoul.pickple.data.api.request.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignUpRequest(
    var email : String,
    var password : String,
    var nickname : String
) : Parcelable