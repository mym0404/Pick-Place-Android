package korea.seoul.pickple.data.entity

import android.os.Parcelable
import androidx.room.*
import com.google.gson.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import korea.seoul.pickple.data.entity.Place.PlaceTypeConverter
import korea.seoul.pickple.data.entity.Place.PlaceTypeJsonSerializer
import kotlinx.android.parcel.Parcelize

/**
 * 장소를 의미하는 클래스
 */
@Parcelize
@Entity(
    tableName = "Places",
    indices = arrayOf(
        Index(value = ["place_name"], unique = true)
    )
)
data class Place(
    /**
     * Entity의 Id, Primary Key
     */
    @SerializedName("id")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    /**
     * 장소의 타입
     *
     * e.g.)음식점, 고궁 ...
     *
     * @see Place.Type
     * @see PlaceTypeConverter
     * @see PlaceTypeJsonSerializer
     */
    @SerializedName("place_type")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "place_type")
    @JsonAdapter(PlaceTypeJsonSerializer::class)
    @TypeConverters(PlaceTypeConverter::class)
    val type: Type,

    /**
     * 장소 이름, unique constraint
     *
     * e.g.)성우 이용원
     */
    @SerializedName("place_name")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "place_name")
    val name: String,

    /**
     * 장소의 짧은 설명
     *
     * e.g.)그때 그시저로 타임슬립
     */
    @SerializedName("place_description")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "place_description")
    val description: String,

    /**
     * 장소의 전화번호 (nullable)
     *
     * e.g.)02-323-7871
     */
    @SerializedName("place_phone")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "place_phone")
    val phoneNumber: String?,

    /**
     * 장소의 위치 (nullable)
     *
     * @see Location
     */
    @SerializedName("place_location")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "place_location")
    @Embedded
    var location: Location,
    /**
     * 장소 이용 가격 (nullable)
     *
     * e.g.)38000
     */
    @SerializedName("place_price")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "place_price")
    var price: Int?,

    /**
     * 장소의 좋아요 횟수
     */
    @SerializedName("place_like")
    @Expose(serialize = false, deserialize = true)
    @ColumnInfo(name = "place_like")
    var likeCount: Int,


    @SerializedName("place_thumbnail")
    @Expose(serialize = true, deserialize = true)
    @ColumnInfo(name = "place_thumbnail")
    val thumbnail: String

) : Parcelable {
    /**
     * 장소의 타입에 해당하는 enum class
     *
     * e.g.)음식점, 고궁 ...
     */
    enum class Type(val type: Int) {
        UNKNOWN(-1),
        FOOD(0),
        PALACE(1),

        ;

        companion object {
            /**
             * [Int] 값으로 [Type] 값을 반환받을 수 있는 정적 메서드
             *
             * @param value int value matched with value of [type] property in [Type]
             * @return [Type] object matched with parameter [value]
             */
            fun parse(value: Int): Type {
                return when (value) {
                    Type.FOOD.type -> Type.FOOD
                    Type.PALACE.type -> Type.PALACE
                    else -> Type.UNKNOWN
                }
            }
        }

    }

    /**
     * [Room] 데이터베이스에서 [Type] 형 객체를 [Int] 와 형변환하기 위해 사용될 컨버터 클래스
     */
    class PlaceTypeConverter {
        @TypeConverter
        fun fromIntToType(value: Int) = Place.Type.parse(value)

        @TypeConverter
        fun fromTypeToIntValue(type: Type) = type.type
    }

    /**
     * [Gson]을 이용한 [Retrofit] 통신에서 [Type] 와 [Int] 를 (De)Serialization 하기위해 사용될 [JsonSerializer]
     */
    class PlaceTypeJsonSerializer : JsonSerializer<Place.Type>, JsonDeserializer<Place.Type> {
        override fun serialize(src: Type?, typeOfSrc: java.lang.reflect.Type?, context: JsonSerializationContext?): JsonElement {
            return try {
                JsonPrimitive(src!!.type)
            } catch (e: Exception) {
                JsonPrimitive(Type.UNKNOWN.type)
            }
        }

        override fun deserialize(json: JsonElement?, typeOfT: java.lang.reflect.Type?, context: JsonDeserializationContext?): Type {
            return try {
                Type.parse(json!!.asInt)
            } catch (e: Exception) {
                Type.UNKNOWN
            }
        }
    }

}



