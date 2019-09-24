package korea.seoul.pickple.data.api.response.review
import android.annotation.SuppressLint
import android.os.Parcelable
import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.api.dto.ReviewDTO
import korea.seoul.pickple.data.entity.Review

import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 24, September, 2019
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ReviewListResponse(
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
    var data: List<Data>?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("info")
        @Expose(serialize = true, deserialize = true)
        var info: List<ReviewDTO>
    ) : Parcelable

    /**
     * course review는 placeIdx가 null이고
     * place review는 courseIdx가 null이다.
     *
     * 뭐든 반환하는 개떡같은 코드를 만들어보자.
     * */
    fun toEntityWithIdx(idx: Int) : List<Review> {
        Log.d("seungmin", "$idx review $data")
        return data?.filter {
            it.info[0].courseIdx == idx || it.info[0].placeIdx == idx
        }?.map {
            it.info[0].toEntity()
        }?: listOf()
    }
}