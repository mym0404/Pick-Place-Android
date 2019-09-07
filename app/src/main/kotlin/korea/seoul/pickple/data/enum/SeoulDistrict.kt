package korea.seoul.pickple.data.enum

enum class SeoulDistrict(val code : Int) {
    UNKNOWN(-1),

    JONGRO(0),
    JOONG(1),
    YONGSAN(2),

    DOBONG(3),
    NOWON(4),
    GANGBUK(5),
    SEONGBUK(6),
    JOONGRANG(7),
    DONGDAEMUN(8),
    SEONGDONG(9),
    GWANGJIN(10),

    EUNPYEONG(11),
    MAPO(12),
    SEADAEMUN(13),

    GANGSEO(14),
    GWANAK(15),
    GURO(16),
    GEUMCHEON(17),
    DONGJAK(18),
    YANGCHEON(19),
    YEONGDEUNGPO(20),

    SEOCHO(21),
    GANGNAM(22),
    SONGPA(23),
    GANGDONG(24)

    ;

    companion object {
        fun getAllDistrict() = SeoulDistrict.values()

        fun parse(code : Int)  = getAllDistrict().firstOrNull { it.code == code } ?: UNKNOWN
    }
}