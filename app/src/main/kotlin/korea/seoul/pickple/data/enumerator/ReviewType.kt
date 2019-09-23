package korea.seoul.pickple.data.enumerator

/**
 * Created by mj on 24, September, 2019
 */

enum class ReviewType(val value : Int) {
    COURSE(1),
    PLACE(2)

    ;

    fun parse(value : Int) : ReviewType {
        return ReviewType.values().firstOrNull { value == it.value } ?: COURSE
    }
}