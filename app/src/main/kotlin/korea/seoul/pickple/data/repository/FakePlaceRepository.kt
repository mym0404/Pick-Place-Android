package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place
import retrofit2.Call
import retrofit2.mock.Calls
import kotlin.random.Random

class FakePlaceRepository : PlaceRepository {

    override fun getPlaceWithId(id: Int): Call<Place> {
        return Calls.response(
            Place(
                id = id,
                type = Place.Type.FOOD,
                name = "${listOf("명주","수민","승민","소민").random()}네 집",
                description = "${listOf("깨끗","아늑","더럽기까지","기괴","심심").random()}함",
                phoneNumber = "010-${Random.nextInt(1000,10000)}-${Random.nextInt(1000,10000)}",
                location = listOf(
                    Location(37.6371,127.0247),
                    Location(37.4766,126.9816),
                    Location(37.4626,126.9383)
                ).random(),
                price = Random.nextInt(5000,300000000),
                likeCount = 999,
                thumbnail = "https://previews.123rf.com/images/beholdereye/beholdereye1305/beholdereye130500006/19454749-sound-waves-oscillating-on-black-background-vector-file-included.jpg"
            )
        )
    }
}