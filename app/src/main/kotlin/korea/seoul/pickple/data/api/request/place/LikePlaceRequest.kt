package korea.seoul.pickple.data.api.request.place

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by mj on 22, September, 2019
 */

@Parcelize
data class LikePlaceRequest(
    var placeIdx : Int
) : Parcelable