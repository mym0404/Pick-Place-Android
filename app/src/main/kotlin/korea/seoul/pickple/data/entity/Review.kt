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
        EMOTION1,
        EMOTION2,
        EMOTION3,
        EMOTION4,
        EMOTION5,

        ;

        /**
         * TODO 감정표현 리소스 관리는 여기서 한다.
         * */
        fun toDrawableResourceId(): Int {
            return when (this) {
                EMOTION1 -> R.color.colorAccent
                EMOTION2 -> R.color.colorBlack
                EMOTION3 -> R.color.colorPrimary
                EMOTION4 -> R.color.colorWhite
                EMOTION5 -> R.color.colorBlack
            }
        }
    }
}