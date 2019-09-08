package korea.seoul.pickple.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsAPI {
    @GET("/")
    fun getRouteFromTwoPlace(
        @Query("origin") origin : String,
        @Query("destination") destination : String,
        @Query("key") key : String,
        @Query("waypoints") waypoints : String? = null,
        @Query("mode") mode : String = "driving",
        @Query("units") units : String = "metric",
        @Query("region") region : String = "ko"
        ) : Call<Unit>

}