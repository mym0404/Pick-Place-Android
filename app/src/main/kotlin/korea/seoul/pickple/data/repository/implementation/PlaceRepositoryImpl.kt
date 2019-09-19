package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.PlaceAPI
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.interfaces.PlaceRepository
import retrofit2.Call

class PlaceRepositoryImpl(private val placeAPI: PlaceAPI) : PlaceRepository {
    override fun getPlaceWithId(id: Int): Call<Place> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}