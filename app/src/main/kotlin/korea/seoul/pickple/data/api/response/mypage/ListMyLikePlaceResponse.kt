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
data class ListMyLikePlaceResponse(
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
    var `data`: List<Data>?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("info")
        @Expose(serialize = true, deserialize = true)
        var info: List<PlaceDTO>
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class PlaceDTO(
            @SerializedName("placeIdx")
            @Expose(serialize = true, deserialize = true)
            var placeIdx: Int,
            @SerializedName("pName")
            @Expose(serialize = true, deserialize = true)
            var pName: String,
            @SerializedName("pDescription")
            @Expose(serialize = true, deserialize = true)
            var pDescription: String,
            @SerializedName("place_thumbnail")
            @Expose(serialize = true, deserialize = true)
            var placeThumbnail: String,
            @SerializedName("place_like")
            @Expose(serialize = true, deserialize = true)
            var placeLike: Int,
            @SerializedName("userIdx")
            @Expose(serialize = true, deserialize = true)
            var userIdx: Int
        ) : Parcelable
    }
}