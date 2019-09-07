package korea.seoul.pickple.data.entity

import android.os.Parcelable
import androidx.room.*
import com.google.gson.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Course.*
import korea.seoul.pickple.data.enum.SeoulDistrict
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
    val districtInSeoul : SeoulDistrict,

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
    val places : List<Int>,

    /**
     * 코스의 좋아요 횟수
     *
     * 서버로 클라이언트의 좋아요 횟수를 전송할 필요가 없기 때문에 serialize = false 로 설정
     */
    @SerializedName("course_like")
    @Expose(serialize = false, deserialize = true)
    @ColumnInfo(name = "course_like")
    val likeCount : Int
) : Parcelable {

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