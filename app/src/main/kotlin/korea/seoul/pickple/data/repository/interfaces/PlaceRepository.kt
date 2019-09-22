package korea.seoul.pickple.data.repository.interfaces

import android.net.Uri
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.place.GetPlaceResponse
import korea.seoul.pickple.data.api.response.place.SearchPlaceResponse
import korea.seoul.pickple.data.entity.Place
import retrofit2.Call

interface PlaceRepository {

    fun getPlace(id : Int) : Call<GetPlaceResponse>
    fun enrollPlace(place : Place, thumbnail: Uri) : Call<BaseResponse>
    fun searchPlace(keyword : String) : Call<SearchPlaceResponse>
    fun likePlace(placeIdx : Int) : Call<BaseResponse>
    fun unlikePlace(placeIdx : Int) : Call<BaseResponse>


}