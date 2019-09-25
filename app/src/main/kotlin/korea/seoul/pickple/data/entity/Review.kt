package korea.seoul.pickple.data.entity

import android.os.Parcelable
import korea.seoul.pickple.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val id: Int,

    val thumbnail : String,

    val name : String,

    val likeCount : Int,

    val reviewCount : Int,

    /**
     * Review의 내용
     * */
    val comment: String,

    val commenter: String,

    val createdAt : String,

    val emotion: Emoticon
) : Parcelable {
    enum class Emoticon(val value : Int) {
        EMOTION1(0),
        EMOTION2(1),
        EMOTION3(2),
        EMOTION4(3),
        EMOTION5(4),

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
        companion object {
            @JvmStatic
            fun parse(value : Int) = Emoticon.values().firstOrNull{value == it.value} ?: Emoticon.EMOTION1
        }
    }


}