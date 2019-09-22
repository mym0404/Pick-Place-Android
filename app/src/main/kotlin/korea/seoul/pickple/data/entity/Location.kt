package korea.seoul.pickple.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 위도와 경도를 의미하는 속성을 갖고 있는 위치 정보에 해당하는 클래스
 */
@Parcelize
data class Location(
    @SerializedName("latitude")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name="latitude")
    val latitude : Double,

    @SerializedName("longitude")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name="longitude")
    val longitude : Double,

    @SerializedName("address")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name="longitude")
    val address : String?
) : Parcelable {
    fun toLatLng() = LatLng(latitude,longitude)
}