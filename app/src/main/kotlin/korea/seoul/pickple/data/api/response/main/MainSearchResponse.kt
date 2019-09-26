package korea.seoul.pickple.data.api.response.main
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Course

import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class MainSearchResponse(
    @SerializedName("status")
    @Expose(serialize = true, deserialize = true)
    var status: Int,
    @SerializedName("success")
    @Expose(serialize = true, deserialize = true)
    var success: Boolean,
    @SerializedName("message")
    @Expose(serialize = true, deserialize = true)
    var message: String,
    @SerializedName("data")
    @Expose(serialize = true, deserialize = true)
    var `data`: List<Course>?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("courseIdx")
        @Expose(serialize = true, deserialize = true)
        var courseIdx: Int,
        @SerializedName("cName")
        @Expose(serialize = true, deserialize = true)
        var cName: String,
        @SerializedName("cThumbnail")
        @Expose(serialize = true, deserialize = true)
        var cThumbnail: String,
        @SerializedName("cLikeCount")
        @Expose(serialize = true, deserialize = true)
        var cLikeCount: String,
        @SerializedName("cReviewCount")
        @Expose(serialize = true, deserialize = true)
        var cReviewCount: Int,
        @SerializedName("courseIcon")
        @Expose(serialize = true, deserialize = true)
        var courseIcon: String,
        @SerializedName("tag")
        @Expose(serialize = true, deserialize = true)
        var tag: List<String>
    ) : Parcelable
}