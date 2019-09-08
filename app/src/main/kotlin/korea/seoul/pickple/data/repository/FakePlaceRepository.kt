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
                description = "${listOf("깨끗","아늑","더럽기까지")}함",
                phoneNumber = "010-${Random.nextInt(1000,10000)}-${Random.nextInt(1000,10000)}",
                location = Location(Random.nextDouble(33.0,43.0), Random.nextDouble(124.0,132.0)),
                price = Random.nextInt(5000,300000000)
            )
        )
    }
}