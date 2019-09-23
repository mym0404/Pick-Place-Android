package korea.seoul.pickple.data.api.request.set
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
data class ChangePasswordRequest(
    @SerializedName("user_pwd")
    @Expose(serialize = true, deserialize = true)
    var userPwd: Int,
    @SerializedName("new_pwd")
    @Expose(serialize = true, deserialize = true)
    var newPwd: Int
) : Parcelable