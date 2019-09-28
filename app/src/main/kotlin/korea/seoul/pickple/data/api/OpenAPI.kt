package korea.seoul.pickple.data.api

import android.annotation.SuppressLint
import retrofit2.Call
import retrofit2.http.GET
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import kotlinx.android.parcel.Parcelize


interface OpenAPI {
    @GET("765071496c7769683836505279484e/json/culturalEventInfo/1/10")
    fun listOpenAPIDatas() : Call<OpenAPIDTO>
}

@SuppressLint("ParcelCreator")
@Parcelize
data class OpenAPIDTO(
    @SerializedName("culturalEventInfo")
    @Expose(serialize = true, deserialize = true)
    var culturalEventInfo: CulturalEventInfo
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class CulturalEventInfo(
        @SerializedName("list_total_count")
        @Expose(serialize = true, deserialize = true)
        var listTotalCount: Int,
        @SerializedName("RESULT")
        @Expose(serialize = true, deserialize = true)
        var rESULT: RESULT,
        @SerializedName("row")
        @Expose(serialize = true, deserialize = true)
        var row: List<Row>
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class RESULT(
            @SerializedName("CODE")
            @Expose(serialize = true, deserialize = true)
            var cODE: String,
            @SerializedName("MESSAGE")
            @Expose(serialize = true, deserialize = true)
            var mESSAGE: String
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Row(
            @SerializedName("CODENAME")
            @Expose(serialize = true, deserialize = true)
            var cODENAME: String,
            @SerializedName("TITLE")
            @Expose(serialize = true, deserialize = true)
            var tITLE: String,
            @SerializedName("DATE")
            @Expose(serialize = true, deserialize = true)
            var dATE: String,
            @SerializedName("PLACE")
            @Expose(serialize = true, deserialize = true)
            var pLACE: String,
            @SerializedName("ORG_NAME")
            @Expose(serialize = true, deserialize = true)
            var oRGNAME: String,
            @SerializedName("USE_TRGT")
            @Expose(serialize = true, deserialize = true)
            var uSETRGT: String,
            @SerializedName("USE_FEE")
            @Expose(serialize = true, deserialize = true)
            var uSEFEE: String,
            @SerializedName("PLAYER")
            @Expose(serialize = true, deserialize = true)
            var pLAYER: String,
            @SerializedName("PROGRAM")
            @Expose(serialize = true, deserialize = true)
            var pROGRAM: String,
            @SerializedName("ETC_DESC")
            @Expose(serialize = true, deserialize = true)
            var eTCDESC: String,
            @SerializedName("ORG_LINK")
            @Expose(serialize = true, deserialize = true)
            var oRGLINK: String,
            @SerializedName("MAIN_IMG")
            @Expose(serialize = true, deserialize = true)
            var mAINIMG: String,
            @SerializedName("RGSTDATE")
            @Expose(serialize = true, deserialize = true)
            var rGSTDATE: String,
            @SerializedName("TICKET")
            @Expose(serialize = true, deserialize = true)
            var tICKET: String,
            @SerializedName("STRTDATE")
            @Expose(serialize = true, deserialize = true)
            var sTRTDATE: String,
            @SerializedName("END_DATE")
            @Expose(serialize = true, deserialize = true)
            var eNDDATE: String,
            @SerializedName("THEMECODE")
            @Expose(serialize = true, deserialize = true)
            var tHEMECODE: String
        ) : Parcelable
    }
}