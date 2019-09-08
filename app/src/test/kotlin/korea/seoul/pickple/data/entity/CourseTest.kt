package korea.seoul.pickple.data.entity

import com.google.gson.GsonBuilder
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import org.junit.After
import org.junit.Before
import org.junit.Test

class CourseTest {

    private val gson = GsonBuilder().create()

    @Before
    fun setUp() {
    }

    @Test
    fun courseSerializationTest() {
        val course = Course(1,"코스 이름","코스 설명",
            SeoulDistrict.DOBONG,listOf(1,2,3,4,5),999)

        val json = gson.toJson(course)
        println(json)
    }

    @After
    fun tearDown() {
    }
}