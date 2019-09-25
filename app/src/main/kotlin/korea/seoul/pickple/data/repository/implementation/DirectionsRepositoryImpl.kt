package korea.seoul.pickple.data.repository.implementation

import korea.seoul.pickple.data.api.DirectionsAPI
import korea.seoul.pickple.data.api.response.directions.DirectionsResponse
import korea.seoul.pickple.data.api.response.directions.GeocodingResponse
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.repository.interfaces.DirectionsRepository
import retrofit2.Call

class DirectionsRepositoryImpl(private val api : DirectionsAPI) : DirectionsRepository {

    override fun getGeocoding(location: Location, key: String): Call<GeocodingResponse> {
        return api.getGeocoding("${location.latitude},${location.longitude}",key)
    }

    override fun getRouteFromTwoPlace  (
        origin : Location,
        destination : Location,
        key : String,
        mode : String?
    ) : Call<DirectionsResponse> {

        return api.getRouteFromTwoPlace(
            origin = "${origin.latitude},${origin.longitude}",
            destination = "${destination.latitude},${destination.longitude}",
            key = key,
            mode = "transit"
        )
    }

}