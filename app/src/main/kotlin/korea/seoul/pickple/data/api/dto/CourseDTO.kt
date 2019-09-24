package korea.seoul.pickple.data.api.dto

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class CourseDTO(
    @SerializedName("courseIdx")
    @Expose(serialize = true, deserialize = true)
    var courseIdx: Int,
    @SerializedName("cName")
    @Expose(serialize = true, deserialize = true)
    var cName: String,
    @SerializedName("userIdx")
    @Expose(serialize = true, deserialize = true)
    var userIdx: Int,
    @SerializedName("courseDate")
    @Expose(serialize = true, deserialize = true)
    var courseDate: Date?,
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
    @SerializedName("courseIcon")
    @Expose(serialize = true, deserialize = true)
    var courseIcon: String?,
    @SerializedName("totalHour")
    @Expose(serialize = true, deserialize = true)
    var totalHour: String?

    ) : Parcelable {
    fun toEntity() : Course {
        return Course(courseIdx, Course.Type.parse(cType), cName, cDescription, SeoulDistrict.parse(cDistrict), null, cLikeCount, null, cThumbnail)
    }
}