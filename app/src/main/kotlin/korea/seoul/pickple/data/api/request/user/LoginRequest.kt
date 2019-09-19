package korea.seoul.pickple.data.api.request.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    @Expose
    var email : String,
    @Expose
    var password : String
): Parcelable