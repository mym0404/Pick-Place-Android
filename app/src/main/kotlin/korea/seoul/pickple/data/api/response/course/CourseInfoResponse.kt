package korea.seoul.pickple.data.api.response.course
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.enumerator.SeoulDistrict

import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 25, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class CourseInfoResponse(
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
    var data: Data?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("courseIdx")
        @Expose(serialize = true, deserialize = true)
        var courseIdx: Int,
        @SerializedName("cName")
        @Expose(serialize = true, deserialize = true)
        var cName: String,
        @SerializedName("cDistrict")
        @Expose(serialize = true, deserialize = true)
        var cDistrict: Int,
        @SerializedName("cType")
        @Expose(serialize = true, deserialize = true)
        var cType: Int,
        @SerializedName("cDescription")
        @Expose(serialize = true, deserialize = true)
        var cDescription: String,
        @SerializedName("cThumbnail")
        @Expose(serialize = true, deserialize = true)
        var cThumbnail: String,
        @SerializedName("cLikeCount")
        @Expose(serialize = true, deserialize = true)
        var cLikeCount: Int,
        @SerializedName("totalHour")
        @Expose(serialize = true, deserialize = true)
        var totalHour: String,
        @SerializedName("cReviewCount")
        @Expose(serialize = true, deserialize = true)
        var cReviewCount: Int,
        @SerializedName("place_1")
        @Expose(serialize = true, deserialize = true)
        var place1: Int?,
        @SerializedName("place_2")
        @Expose(serialize = true, deserialize = true)
        var place2: Int?,
        @SerializedName("place_3")
        @Expose(serialize = true, deserialize = true)
        var place3: Int,
        @SerializedName("place_4")
        @Expose(serialize = true, deserialize = true)
        var place4: Int?,
        @SerializedName("place_5")
        @Expose(serialize = true, deserialize = true)
        var place5: Int?,
        @SerializedName("place_6")
        @Expose(serialize = true, deserialize = true)
        var place6: Int?,
        @SerializedName("place_7")
        @Expose(serialize = true, deserialize = true)
        var place7: Int?,
        @SerializedName("place_8")
        @Expose(serialize = true, deserialize = true)
        var place8: Int?,
        @SerializedName("place_9")
        @Expose(serialize = true, deserialize = true)
        var place9: Int?,
        @SerializedName("place_10")
        @Expose(serialize = true, deserialize = true)
        var place10: Int?,
        @SerializedName("place_11")
        @Expose(serialize = true, deserialize = true)
        var place11: Int?,
        @SerializedName("place_12")
        @Expose(serialize = true, deserialize = true)
        var place12: Int?,
        @SerializedName("tagIdx")
        @Expose(serialize = true, deserialize = true)
        var tagIdx: List<Int>?
    ) : Parcelable {
        fun toEntity() : Course {
            return Course(
                courseIdx,
                Course.Type.parse(cType),
                cName,
                cDescription,
                SeoulDistrict.parse(cDistrict),
                listOf(place1,place2,place3,place4,place5,place6,
                    place7,place8,place9,place10,place11,place12).filterNotNull(),
                cLikeCount,
                listOf(),
                cThumbnail
            )
        }
    }
}