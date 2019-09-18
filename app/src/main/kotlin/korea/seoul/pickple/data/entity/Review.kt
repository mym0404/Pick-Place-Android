package korea.seoul.pickple.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    /**
     * Review의 아이디
     * */
    val id: Int,

    /**
     * Review의 내용
     * */
    val comment: String,

    val commenter: String,

    val emotion: Emotion
) : Parcelable {
    enum class Emotion {
        DEFAULT,

        ;

        fun toDrawableResoureId(): Int {
            return R.drawable.cover_gradation
        }
    }
}