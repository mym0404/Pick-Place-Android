package korea.seoul.pickple.data.api.request.place
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 22, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class EnrollPlaceRequest(
    @SerializedName("placeName")
    @Expose(serialize = true, deserialize = true)
    var placeName: String,
    @SerializedName("description")
    @Expose(serialize = true, deserialize = true)
    var description: String,
    @SerializedName("place_thumbnail")
    @Expose(serialize = true, deserialize = true)
    var placeThumbnail: String,
    @SerializedName("latitude")
    @Expose(serialize = true, deserialize = true)
    var latitude: String,
    @SerializedName("longitude")
    @Expose(serialize = true, deserialize = true)
    var longitude: String,
    @SerializedName("address")
    @Expose(serialize = true, deserialize = true)
    var address: String,
    @SerializedName("businessHour")
    @Expose(serialize = true, deserialize = true)
    var businessHour: String,
    @SerializedName("fee")
    @Expose(serialize = true, deserialize = true)
    var fee: String
) : Parcelable