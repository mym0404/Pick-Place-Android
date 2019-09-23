package korea.seoul.pickple.data.api.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by mj on 23, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class TagDTO(
    @SerializedName("tagIdx")
    @Expose(serialize = true, deserialize = true)
    var tagIdx: Int,
    @SerializedName("tagName")
    @Expose(serialize = true, deserialize = true)
    var tagName: String,
    @SerializedName("tagCount")
    @Expose(serialize = true, deserialize = true)
    var tagCount: Int
) : Parcelable