package korea.seoul.pickple.data.entity

import com.google.gson.GsonBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test

class PlaceTest {

    private val gson = GsonBuilder().create()

    @Before
    fun setUp() {
    }

    @Test
    fun placeSerializationTest() {
        val place = Place(
            1,
            Place.Type.FOOD,
            "장소 이름",
            "장소 짧은 설명",
            "010-1111-1111",
            Location(12.5,5.2),
            38000)

        val json = gson.toJson(place)
        println(json)
    }

    @After
    fun tearDown() {
    }
}