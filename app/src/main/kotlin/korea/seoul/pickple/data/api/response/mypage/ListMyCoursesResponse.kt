package korea.seoul.pickple.data.api.response.mypage
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 25, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ListMyCoursesResponse(
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
    var data: List<Data>?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("info")
        @Expose(serialize = true, deserialize = true)
        var info: List<Info>
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Info(
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
            var cLikeCount: Int
        ) : Parcelable
    }
}