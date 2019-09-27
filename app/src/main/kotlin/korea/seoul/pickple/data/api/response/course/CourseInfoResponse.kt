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
        @SerializedName("placeIdx")
        @Expose
        var placeIndices : List<Int>,
        @SerializedName("tag")
        @Expose(serialize = true, deserialize = true)
        var tagIdx: List<String>,
        @SerializedName("distance")
        @Expose
        var distances:List<Float>
    ) : Parcelable {
        fun toEntity() : Course {
            return Course(
                courseIdx,
                Course.Type.parse(cType),
                cName,
                cDescription,
                SeoulDistrict.parse(cDistrict),
                placeIndices,
                cLikeCount,
                tagIdx,
                cThumbnail,
                totalHour
            )
        }
    }
}