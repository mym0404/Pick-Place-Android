package korea.seoul.pickple.data.api.response.user

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    @Expose
    var status : Int,
    @Expose
    var success : Boolean,
    @Expose
    var message : String,
    @Expose
    var data : String
) : Parcelable