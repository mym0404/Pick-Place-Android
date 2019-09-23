package korea.seoul.pickple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 23, September, 2019
 */

@Parcelize
data class SeoulNews(
    @SerializedName("notice_idx")
    @Expose(serialize = true, deserialize = true)
    var noticeIdx: Int,
    @SerializedName("thumbnail")
    @Expose(serialize = true, deserialize = true)
    var thumbnail: String,
    @SerializedName("url")
    @Expose(serialize = true, deserialize = true)
    var url: String,
    @SerializedName("createdAt")
    @Expose(serialize = true, deserialize = true)
    var createdAt: String
) : Parcelable