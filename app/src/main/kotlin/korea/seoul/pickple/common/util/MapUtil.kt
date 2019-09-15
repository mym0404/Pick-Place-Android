package korea.seoul.pickple.common.util

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import korea.seoul.pickple.data.entity.Place

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

}