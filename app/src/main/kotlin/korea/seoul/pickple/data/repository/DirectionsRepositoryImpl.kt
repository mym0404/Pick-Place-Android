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
        waypoints : List<Location>,
        mode : String?,
        units : String?,
        region : String?
    ) : Call<DirectionsResponse> {

        val waypoints = if(waypoints.isEmpty()) {
            null
        }else {
            waypoints.map { "${it.latitude},${it.longitude}" }.joinToString("|")
        }


        return api.getRouteFromTwoPlace(
            origin = "${origin.latitude},${origin.longitude}",
            destination = "${destination.latitude},${destination.longitude}",
            key = key,
            waypoints = waypoints,
            mode = mode ?: "driving",
            units = units ?: "metric",
            region = region ?: "ko"
        )
    }

}