package korea.seoul.pickple.data.repository.interfaces

import korea.seoul.pickple.data.api.DirectionsResponse
import korea.seoul.pickple.data.api.GeocodingResponse
import korea.seoul.pickple.data.entity.Location
import retrofit2.Call

interface DirectionsRepository {
    fun getRouteFromTwoPlace(
        origin : Location,
        destination : Location,
        key : String,
        mode : String? = "transit"
    ) : Call<DirectionsResponse>

    fun getGeocoding(
        location: Location,
        key : String
    ): Call<GeocodingResponse>
}