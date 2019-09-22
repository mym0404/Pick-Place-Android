package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.request.place.LikePlaceRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.place.GetPlaceResponse
import korea.seoul.pickple.data.api.response.place.SearchPlaceResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PlaceAPI {
    @GET("place/info/{id}")
    fun getPlace(@Path("id") placeIdx : Int) : Call<GetPlaceResponse>

    @POST("place/enroll")
    @Multipart
    fun enrollPlace(
        @Part("placeName") placeName : RequestBody,
        @Part("description") description : RequestBody,
        @Part("place_thumbnail") placeThumbnail : MultipartBody.Part,
        @Part("latitude") latitude : RequestBody,
        @Part("longitude") longitude : RequestBody,
        @Part("address") address : RequestBody,
        @Part("number") number : RequestBody?,
        @Part("businessHour") businessHour : RequestBody?,
        @Part("fee") fee : RequestBody?
    ) : Call<BaseResponse>

    @GET("place/search")
    fun searchPlace(@Query("placeName") keyword : String) : Call<SearchPlaceResponse>

    @POST("place/like")
    fun likePlace(@Body request : LikePlaceRequest) : Call<BaseResponse>

    @DELETE("place/like/{placeIdx}")
    fun unlikePlace(@Path("placeIdx") idx : Int) : Call<BaseResponse>
}