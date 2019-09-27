package korea.seoul.pickple.data.api.response.mypage

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Review
import kotlinx.android.parcel.Parcelize


/**
 * Created by mj on 25, September, 2019
 */
@Parcelize
data class ListMyReviewResponse(
    @SerializedName("status")
    @Expose(serialize = true, deserialize = true)
    var status: Int,
    @SerializedName("success")
    @Expose(serialize = true, deserialize = true)
    var success: Boolean,
    @SerializedName("data")
    @Expose(serialize = true, deserialize = true)
    var data : List<Data>?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("info")
        @Expose(serialize = true, deserialize = true)
        var info: List<ReviewDTO>
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class ReviewDTO(
            @SerializedName("placeIdx",alternate = ["courseIdx"])
            var placeCourseIdx: Int,
            @SerializedName("place_thumbnail",alternate = ["cThumbnail"])
            var thumbnail: String,
            @SerializedName("pName", alternate = ["cName"])
            var name: String,
            @SerializedName("place_like",alternate = ["cLikeCount"])
            var likeCount: Int,
            @SerializedName("pReviewCount",alternate = ["cReviewCount"])
            var reviewCount: Int,
            @SerializedName("createdAt")
            var createdAt: String,
            @SerializedName("comment")
            var comment: String,
            @SerializedName("emotion")
            var emotion: String
        ) : Parcelable {
            fun toEntity() : Review {
                return Review(placeCourseIdx,
                    thumbnail,
                    name,
                    likeCount,
                    reviewCount,
                    comment,
                    "",
                    createdAt,
                    Review.Emoticon.parse(emotion.toInt())
                )
            }
        }
    }
}