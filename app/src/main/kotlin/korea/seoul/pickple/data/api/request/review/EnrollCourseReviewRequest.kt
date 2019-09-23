package korea.seoul.pickple.data.api.request.review

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by mj on 24, September, 2019
 */

@Parcelize
data class EnrollReviewRequest(
    @SerializedName("courseIdx")
    @Expose(serialize = true, deserialize = true)
    var courseIdx: String,
    @SerializedName("comment")
    @Expose(serialize = true, deserialize = true)
    var comment: String,
    @SerializedName("emoticon")
    @Expose
    var emoticon : Int
) : Parcelable