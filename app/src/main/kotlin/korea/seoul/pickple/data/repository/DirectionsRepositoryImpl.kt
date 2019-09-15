package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.api.DirectionsAPI
import korea.seoul.pickple.data.api.DirectionsResponse
import korea.seoul.pickple.data.api.GeocodingResponse
import korea.seoul.pickple.data.entity.Location
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