package korea.seoul.pickple.data.api.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by mj on 22, September, 2019
 */

@Parcelize
data class BaseResponse(
    var status : Int,
    var success : Boolean,
    var message : String
) : Parcelable