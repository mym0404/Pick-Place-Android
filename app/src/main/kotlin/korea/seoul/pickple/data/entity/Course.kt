package korea.seoul.pickple.data.entity

import android.os.Parcelable
import androidx.room.*
import com.google.gson.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Course.*
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import kotlinx.android.parcel.Parcelize

/**
 * 코스를 의미하는 클래스
 */
@Parcelize
@Entity(
    tableName = "Courses"
)
data class Course(
    /**
     * Entity의 Id, Primary Key
     */
    @SerializedName("id")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    /**
     * 코스의 타입
     */
    @SerializedName("course_type")
    @Expose(serialize = true,deserialize = true)
    @ColumnInfo(name="course_type")
    @JsonAdapter(CourseTypeJsonSerializer::class)
    @TypeConverters(CourseTypeConverter::class)
    val type : Type?,

    /**
     * 코스의 이름
     */
    @SerializedName("course_name")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "course_name")
    val name : String,

    /**
     * 코스의 이름
     */
    @SerializedName("course_description")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "course_description")
    val description : String?,

    /**
     * 해당 코스가 어느 서울 행정구역에 포함되는 지에대한 정보
     *
     * e.g.)노원구
     *
     * @see SeoulDistrict
     * @see SeoulDistrictConverter
     * @see SeoulDistrictJsonSerializer
     */
    @SerializedName("course_district")
    @Expose(serialize = true,deserialize = true)
    @ColumnInfo(name = "course_district")
    @JsonAdapter(SeoulDistrictJsonSerializer::class)
    @TypeConverters(SeoulDistrictConverter::class)
    val districtInSeoul : SeoulDistrict?,

    /**
     * 해당 코스가 포함하고 있는 [Place] 들
     *
     * @see Place
     * @see CoursePlacesConverter
     */
    @SerializedName("course_places")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "course_places")
    @TypeConverters(CoursePlacesConverter::class)
    val places : List<Int>?,

    /**
     * 코스의 좋아요 횟수
     *
     * 서버로 클라이언트의 좋아요 횟수를 전송할 필요가 없기 때문에 serialize = false 로 설정
     */
    @SerializedName("course_like")
    @Expose(serialize = false, deserialize = true)
    @ColumnInfo(name = "course_like")
    val likeCount : Int,

    /**
     * 코스 태그 리스트
     */
    @SerializedName("course_tags")
    @Expose(serialize = true,deserialize = true)
    @ColumnInfo(name="course_tags")
    val tagList : List<String>?,

    @SerializedName("course_thumbnail")
    @Expose(serialize = true,deserialize = true)
    @ColumnInfo(name="course_thumbnail")
    val thumbnail : String,

    @SerializedName("course_hours")
    @Expose(serialize = true,deserialize = true)
    @ColumnInfo(name="course_hours")
    val totalHours : String = "",

    @SerializedName("review_count")
    @Expose(serialize = true,deserialize = true)
    @ColumnInfo(name="review_count")
    val review_count : Int?
) : Parcelable {

    /**
     * 장소의 타입에 해당하는 enum class
     *
     * e.g.)음식점, 고궁 ...
     */
    @Parcelize
    enum class Type(val type : Int) : Parcelable {
        UNKNOWN(-1),
        ORAEGAGE(0),
        KOREA_TRADITIONAL(1),
        CUSTOM(2),

        ;

        companion object {
            /**
             * [Int] 값으로 [Type] 값을 반환받을 수 있는 정적 메서드
             *
             * @param value int value matched with value of [type] property in [Type]
             * @return [Type] object matched with parameter [value]
             */
            fun parse(value : Int) : Type {
                return when(value) {
                    Type.ORAEGAGE.type -> Type.ORAEGAGE
                    Type.KOREA_TRADITIONAL.type -> Type.KOREA_TRADITIONAL
                    Type.CUSTOM.type -> Type.CUSTOM
                    else -> Type.UNKNOWN
                }
            }
        }

        /**
         * Type에 해당하는 코스 한글 이름을 반환
         *
         * @param type [Type] 객체
         * @return [Type] 객체를 한글로 바꾼 것
         * */
        override fun toString() = when(this) {
            ORAEGAGE -> "오래가게코스"
            KOREA_TRADITIONAL -> "한국 전통 코스"
            CUSTOM -> "서울 시민이 추천하는 코스"
            UNKNOWN -> "추천 코스"
        }
    }

    /**
     * [Room] 데이터베이스에서 [List] 형 객체를 [String] 와 형변환하기 위해 사용될 컨버터 클래스
     */
    class CoursePlacesConverter {
        @TypeConverter
        fun fromPlaneStringToList(data : String?) : List<Int> {
            data ?: return listOf()

            return data.split(',').map { it.toInt() }
        }

        @TypeConverter
        fun fromListToPlaneString(list : List<Int>?) : String {
            list ?: return ""

            return list.joinToString(",")
        }
    }

    /**
     * [Room] 데이터베이스에서 [Type] 형 객체를 [Int] 와 형변환하기 위해 사용될 컨버터 클래스
     */
    class CourseTypeConverter {
        @TypeConverter
        fun fromIntToType(value : Int) = Course.Type.parse(value)
        @TypeConverter
        fun fromTypeToIntValue(type : Course.Type) = type.type
    }

    /**
     * [Gson]을 이용한 [Retrofit] 통신에서 [Type] 와 [Int] 를 (De)Serialization 하기위해 사용될 [JsonSerializer]
     */
    class CourseTypeJsonSerializer : JsonSerializer<Course.Type>, JsonDeserializer<Course.Type> {
        override fun serialize(src: Course.Type?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
            return try {
                JsonPrimitive(src!!.type)
            }catch(e: Exception) {
                JsonPrimitive(Course.Type.UNKNOWN.type)
            }
        }

        override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): Course.Type {
            return try{
                Course.Type.parse(json!!.asInt)
            }catch(e : Exception) {
                Course.Type.UNKNOWN
            }
        }
    }

    /**
     * [Room] 데이터베이스에서 [SeoulDistrict] 형 객체를 [Int] 와 형변환하기 위해 사용될 컨버터 클래스
     */
    class SeoulDistrictConverter {
        @TypeConverter
        fun fromCodeToDistrict(code : Int) = SeoulDistrict.parse(code)
        @TypeConverter
        fun fromDistrictToCode(district : SeoulDistrict) = district.code
    }

    /**
     * [Gson]을 이용한 [Retrofit] 통신에서 [SeoulDistrict] 와 [Int] 를 (De)Serialization 하기위해 사용될 [JsonSerializer]
     */
    class SeoulDistrictJsonSerializer : JsonSerializer<SeoulDistrict>, JsonDeserializer<SeoulDistrict> {
        override fun serialize(src: SeoulDistrict?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
            return try {
                JsonPrimitive(src!!.code)
            }catch(e : Exception) {
                JsonPrimitive(SeoulDistrict.UNKNOWN.code)
            }
        }
        override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): SeoulDistrict {
            return try {
                SeoulDistrict.parse(json!!.asInt)
            }catch(e: Exception) {
                SeoulDistrict.UNKNOWN
            }
        }
    }


}