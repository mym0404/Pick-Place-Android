package korea.seoul.pickple.data.api

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DirectionsAPI {
    @GET("maps/api/directions/json")
    fun getRouteFromTwoPlace(
        @Query("origin") origin : String,
        @Query("destination") destination : String,
        @Query("key") key : String,
        @Query("mode") mode : String?
        ) : Call<DirectionsResponse>

    @GET("maps/api/geocode/json")
    fun getGeocoding(
        @Query("latlng") latlng : String,
        @Query("key") key : String
    ) : Call<GeocodingResponse>

}

@SuppressLint("ParcelCreator")
@Parcelize
data class GeocodingResponse(
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("status")
    var status: String
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Result(
        @SerializedName("address_components")
        var addressComponents: List<AddressComponent>,
        @SerializedName("formatted_address")
        var formattedAddress: String,
        @SerializedName("geometry")
        var geometry: Geometry,
        @SerializedName("place_id")
        var placeId: String,
        @SerializedName("types")
        var types: List<String>
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class AddressComponent(
            @SerializedName("long_name")
            var longName: String,
            @SerializedName("short_name")
            var shortName: String,
            @SerializedName("types")
            var types: List<String>
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Geometry(
            @SerializedName("location")
            var location: Location,
            @SerializedName("location_type")
            var locationType: String,
            @SerializedName("viewport")
            var viewport: Viewport
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Location(
                @SerializedName("lat")
                var lat: Double,
                @SerializedName("lng")
                var lng: Double
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Viewport(
                @SerializedName("northeast")
                var northeast: Northeast,
                @SerializedName("southwest")
                var southwest: Southwest
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Southwest(
                    @SerializedName("lat")
                    var lat: Double,
                    @SerializedName("lng")
                    var lng: Double
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Northeast(
                    @SerializedName("lat")
                    var lat: Double,
                    @SerializedName("lng")
                    var lng: Double
                ) : Parcelable
            }
        }
    }
}

@SuppressLint("ParcelCreator")
@Parcelize
data class DirectionsResponse(
    @SerializedName("available_travel_modes")
    var available : List<String>,
    @SerializedName("status")
    var status: String,
    @SerializedName("geocoded_waypoints")
    var geocodedWaypoints: List<GeocodedWaypoint>,
    @SerializedName("routes")
    var routes: List<Route>,
    @SerializedName("copyrights")
    var copyrights: String,
    @SerializedName("overview_polyline")
    var overviewPolyline: OverviewPolyline,
    @SerializedName("warnings")
    var warnings: List<String>,
    @SerializedName("waypoint_order")
    var waypointOrder: List<Int>,
    @SerializedName("bounds")
    var bounds: Bounds
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Route(
        @SerializedName("summary")
        var summary: String,
        @SerializedName("legs")
        var legs: List<Leg>,
        @SerializedName("duration")
        var duration: Duration,
        @SerializedName("distance")
        var distance: Distance,
        @SerializedName("start_location")
        var startLocation: StartLocation,
        @SerializedName("end_location")
        var endLocation: EndLocation,
        @SerializedName("start_address")
        var startAddress: String,
        @SerializedName("end_address")
        var endAddress: String,
        @SerializedName("overview_polyline")
        var overviewPolyline: Leg.Step.Polyline
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class StartLocation(
            @SerializedName("lat")
            var lat: Double,
            @SerializedName("lng")
            var lng: Double
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Leg(
            @SerializedName("steps")
            var steps: List<Step>
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Step(
                @SerializedName("travel_mode")
                var travelMode: String,
                @SerializedName("start_location")
                var startLocation: StartLocation,
                @SerializedName("end_location")
                var endLocation: EndLocation,
                @SerializedName("polyline")
                var polyline: Polyline,
                @SerializedName("duration")
                var duration: Duration,
                @SerializedName("html_instructions")
                var htmlInstructions: String,
                @SerializedName("distance")
                var distance: Distance
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class EndLocation(
                    @SerializedName("lat")
                    var lat: Double,
                    @SerializedName("lng")
                    var lng: Double
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Polyline(
                    @SerializedName("points")
                    var points: String
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Duration(
                    @SerializedName("value")
                    var value: Int,
                    @SerializedName("text")
                    var text: String
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Distance(
                    @SerializedName("value")
                    var value: Int,
                    @SerializedName("text")
                    var text: String
                ) : Parcelable

                @SuppressLint("ParcelCreator")
                @Parcelize
                data class StartLocation(
                    @SerializedName("lat")
                    var lat: Double,
                    @SerializedName("lng")
                    var lng: Double
                ) : Parcelable
            }
        }

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class EndLocation(
            @SerializedName("lat")
            var lat: Double,
            @SerializedName("lng")
            var lng: Double
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Duration(
            @SerializedName("value")
            var value: Int,
            @SerializedName("text")
            var text: String
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Distance(
            @SerializedName("value")
            var value: Int,
            @SerializedName("text")
            var text: String
        ) : Parcelable
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class GeocodedWaypoint(
        @SerializedName("geocoder_status")
        var geocoderStatus: String,
        @SerializedName("place_id")
        var placeId: String,
        @SerializedName("types")
        var types: List<String>
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Bounds(
        @SerializedName("southwest")
        var southwest: Southwest,
        @SerializedName("northeast")
        var northeast: Northeast
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Northeast(
            @SerializedName("lat")
            var lat: Double,
            @SerializedName("lng")
            var lng: Double
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Southwest(
            @SerializedName("lat")
            var lat: Double,
            @SerializedName("lng")
            var lng: Double
        ) : Parcelable
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class OverviewPolyline(
        @SerializedName("points")
        var points: String
    ) : Parcelable
}
