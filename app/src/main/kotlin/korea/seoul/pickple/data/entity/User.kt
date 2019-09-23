package korea.seoul.pickple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by mj on 23, September, 2019
 */
@Parcelize
data class User(
    @SerializedName("email")
    @Expose(serialize = true, deserialize = true)
    var email: String,
    @SerializedName("nickname")
    @Expose(serialize = true, deserialize = true)
    var nickname: String
) : Parcelable