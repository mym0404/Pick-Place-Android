package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.api.DirectionsAPI
import korea.seoul.pickple.data.entity.Location

class DirectionsRepositoryImpl(private val api : DirectionsAPI) : DirectionsRepository {

    override fun getRouteFromTwoPlace(
        origin : Location,
        destination : Location,
        key : String,
        waypoints : List<Location>,
        mode : String?,
        units : String?,
        region : String?
    ) {

        val waypoints = if(waypoints.isEmpty()) {
            null
        }else {
            waypoints.map { "${it.latitude},${it.longitude}" }.joinToString("%2C")
        }

        api.getRouteFromTwoPlace(
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