package korea.seoul.pickple.data.api.response.course

import android.os.Parcelable
import korea.seoul.pickple.data.api.dto.TagDTO
import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 23, September, 2019
 */

@Parcelize
data class GetHashTagResponse(
    var status: Int,
    var success: Boolean,
    var message: String,
    var tags: List<TagDTO>
) : Parcelable