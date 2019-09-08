package korea.seoul.pickple.data.repository

import korea.seoul.pickple.data.entity.Location

interface DirectionsRepository {
    fun getRouteFromTwoPlace(
        origin : Location,
        destination : Location,
        key : String,
        waypoints : List<Location> = listOf(),
        mode : String? = "driving",
        units : String? = "metric",
        region : String? = "ko"
    )
}