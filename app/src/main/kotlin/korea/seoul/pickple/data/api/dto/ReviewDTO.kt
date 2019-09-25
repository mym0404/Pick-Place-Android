package korea.seoul.pickple.data.api.dto

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by mj on 24, September, 2019
 */

@Parcelize
data class ReviewDTO(
    @SerializedName("reviewIdx")
    @Expose(serialize = true, deserialize = true)
    var reviewIdx: Int,
    @SerializedName("userIdx")
    @Expose(serialize = true, deserialize = true)
    var userIdx: Int,
    @SerializedName("reviewType")
    @Expose(serialize = true, deserialize = true)
    var reviewType: Int,
    @SerializedName("courseIdx")
    @Expose(serialize = true, deserialize = true)
    var courseIdx: Int?,
    @SerializedName("nickname")
    @Expose(serialize = true, deserialize = true)
    var nickname: String,
    @SerializedName("createdAt")
    @Expose(serialize = true, deserialize = true)
    var createdAt: String,
    @SerializedName("comment")
    @Expose(serialize = true, deserialize = true)
    var comment: String,
    @SerializedName("emotion")
    @Expose(serialize = true, deserialize = true)
    var emotion: Int,
    @SerializedName("placeIdx")
    @Expose(serialize = true, deserialize = true)
    var placeIdx: Int?
) : Parcelable {
}