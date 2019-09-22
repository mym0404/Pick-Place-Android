package korea.seoul.pickple.data.api.response.directions

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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