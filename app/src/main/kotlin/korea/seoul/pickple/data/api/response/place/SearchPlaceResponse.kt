package korea.seoul.pickple.data.api.response.place

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.api.dto.PlaceDTO
import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 22, September, 2019
 */

@Parcelize
data class SearchPlaceResponse(
    var status: Int,
    var success: Boolean,
    var message: String,
    @SerializedName("data")
    var placeData: List<PlaceDTO>?
) : Parcelable