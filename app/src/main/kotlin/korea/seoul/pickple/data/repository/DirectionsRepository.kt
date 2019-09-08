package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.api.DirectionsResponse
import korea.seoul.pickple.data.api.GeocodingResponse
import korea.seoul.pickple.data.entity.Location
import retrofit2.Call

interface DirectionsRepository {
    fun getRouteFromTwoPlace(
        origin : Location,
        destination : Location,
        key : String,
        waypoints : List<Location> = listOf(),
        mode : String? = "driving",
        units : String? = "metric",
        region : String? = "ko"
    ) : Call<DirectionsResponse>

    fun getGeocoding(
        location: Location,
        key : String
    ): Call<GeocodingResponse>
}