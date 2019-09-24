package korea.seoul.pickple.data.api.response.mypage

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 25, September, 2019
 */
@Parcelize
data class ListMyReviewResponse(
    @SerializedName("status")
    @Expose(serialize = true, deserialize = true)
    var status: Int,
    @SerializedName("success")
    @Expose(serialize = true, deserialize = true)
    var success: Boolean,
    @SerializedName("data")
    @Expose(serialize = true, deserialize = true)
    var data : List<Data>?
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
            @SerializedName("placeIdx")
            @Expose(serialize = true, deserialize = true)
            var placeIdx: Int,
            @SerializedName("place_thumbnail")
            @Expose(serialize = true, deserialize = true)
            var placeThumbnail: String,
            @SerializedName("pName")
            @Expose(serialize = true, deserialize = true)
            var pName: String,
            @SerializedName("place_like")
            @Expose(serialize = true, deserialize = true)
            var placeLike: Int,
            @SerializedName("pReviewCount")
            @Expose(serialize = true, deserialize = true)
            var pReviewCount: Int,
            @SerializedName("createdAt")
            @Expose(serialize = true, deserialize = true)
            var createdAt: String,
            @SerializedName("comment")
            @Expose(serialize = true, deserialize = true)
            var comment: String,
            @SerializedName("emotion")
            @Expose(serialize = true, deserialize = true)
            var emotion: String
        ) : Parcelable {
        }
    }
}