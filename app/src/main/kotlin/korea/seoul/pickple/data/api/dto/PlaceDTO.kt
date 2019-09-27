package korea.seoul.pickple.data.api.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place
import kotlinx.android.parcel.Parcelize

/**
 * Created by mj on 22, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class PlaceDTO(
    @SerializedName("placeIdx")
    var id : Int,
    var placeName: String,
    var description: String,
    @SerializedName("place_thumbnail")
    var placeThumbnail: String,
    var placeLike: Int,
    var address: String,
    var number: String,
    var fee: String?,
    var businessHour: String?,
    var location: Location
) : Parcelable {
    fun toEntity() : Place {
        return Place(
            id,
            Place.Type.UNKNOWN,
            placeName,
            description,
            number,
            Location(location.latitude,location.longitude,address),
            (fee ?: "0").toIntOrNull() ?: 0,
            placeLike,
            placeThumbnail,
            address.replace("\\n", "\n"),
            businessHour?.replace("\\n", "\n")?:"",
            fee?.replace("\\n", "\n")?:""
        )
    }
}