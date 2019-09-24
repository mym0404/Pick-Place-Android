package korea.seoul.pickple.data.api.dto
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 24, September, 2019
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class TokenDTO(
    @SerializedName("token")
    @Expose(serialize = true, deserialize = true)
    var token: String,
    @SerializedName("refreshToken")
    @Expose(serialize = true, deserialize = true)
    var refreshToken: String
) : Parcelable