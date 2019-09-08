package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.entity.Place
import retrofit2.Call

interface PlaceRepository {

    fun getPlaceWithId(id : Int) : Call<Place>

}