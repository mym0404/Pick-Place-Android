package korea.seoul.pickple.common.util

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import korea.seoul.pickple.data.entity.Place
import kotlin.math.sqrt

class MapUtil {
    fun autoZoomLevel(places: List<Place>): CameraUpdate? {
        if(places.isEmpty()) return null

        val locations = places.mapNotNull { it.location }

        if(locations.isEmpty()) return null


        if (locations.size == 1) {
            return CameraUpdateFactory.newLatLngZoom(locations.first().toLatLng(), 13f)
        } else {
            val builder = LatLngBounds.Builder()
            for (latlng in locations.map { it.toLatLng() }) {
                builder.include(latlng)
            }
            val bounds = builder.build()

            val padding = 200 // offset from edges of the map in pixels

            return CameraUpdateFactory.newLatLngBounds(bounds, padding)
        }
    }

    fun getNearestPlaceWithMarker(places: List<Place>, marker: Marker): Place? {
        return places.minBy {
            val lat = it.location?.latitude ?: 100.0
            val lng = it.location?.longitude ?: 100.0

            val markerLat = marker.position.latitude
            val markerLng = marker.position.longitude


            sqrt((lat - markerLat) * (lat - markerLat) + (lng - markerLng) * (lng - markerLng))
        }
    }

}