package korea.seoul.pickple.data.api.response.set
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.User

import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 23, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class GetUserInfoResponse(
    @SerializedName("status")
    @Expose(serialize = true, deserialize = true)
    var status: Int,
    @SerializedName("success")
    @Expose(serialize = true, deserialize = true)
    var success: Boolean,
    @SerializedName("message")
    @Expose(serialize = true, deserialize = true)
    var message: String?,
    @SerializedName("data")
    @Expose(serialize = true, deserialize = true)
    var data: List<User>?
) : Parcelable