package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.response.directions.DirectionsResponse
import korea.seoul.pickple.data.api.response.directions.GeocodingResponse
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



