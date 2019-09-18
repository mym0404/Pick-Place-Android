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
                EMOTION1 -> R.drawable.navi_btn_share_w
                EMOTION2 -> R.drawable.cover_gradation
                EMOTION3 -> R.drawable.navi_btn_back_b
                EMOTION4 -> R.drawable.navi_btn_heart_w
                EMOTION5 -> R.drawable.navi_btn_share_w
            }
        }
    }
}