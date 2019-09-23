package korea.seoul.pickple.data.api.response.review
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.api.dto.ReviewDTO

import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 24, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ReviewListResponse(
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
    var data: List<List<Data>>?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("info")
        @Expose(serialize = true, deserialize = true)
        var info: List<ReviewDTO>
    ) : Parcelable
}