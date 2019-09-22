package korea.seoul.pickple.data.repository.implementation

import android.net.Uri
import korea.seoul.pickple.common.util.FileUtil
import korea.seoul.pickple.data.api.PlaceAPI
import korea.seoul.pickple.data.api.request.place.LikePlaceRequest
import korea.seoul.pickple.data.api.response.BaseResponse
import korea.seoul.pickple.data.api.response.place.GetPlaceResponse
import korea.seoul.pickple.data.api.response.place.SearchPlaceResponse
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.interfaces.PlaceRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class PlaceRepositoryImpl(private val placeAPI: PlaceAPI, private val fileUtil: FileUtil) : PlaceRepository {
    override fun getPlace(id: Int): Call<GetPlaceResponse> {
        return placeAPI.getPlace(id)
    }


    override fun enrollPlace(place: Place, thumbnail: Uri): Call<BaseResponse> {

        val thumbnailFile = try {
            File(fileUtil.getPathFromUri(thumbnail)!!)
        } catch (t: Throwable) {
            File("")
        }

        return placeAPI.enrollPlace(
            RequestBody.create(MultipartBody.FORM, place.name),
            RequestBody.create(MultipartBody.FORM, place.description),
            MultipartBody.Part.createFormData(
                "place_thumbnail",
                thumbnailFile.name,
                RequestBody.create(fileUtil.getTypeOfFile(thumbnail),thumbnailFile)
            ),
            RequestBody.create(MultipartBody.FORM, place.location.latitude.toString()),
            RequestBody.create(MultipartBody.FORM, place.location.longitude.toString()),
            RequestBody.create(MultipartBody.FORM, place.location.address ?: ""),
            RequestBody.create(MultipartBody.FORM, ""),
            RequestBody.create(MultipartBody.FORM, ""),
            RequestBody.create(MultipartBody.FORM, (place.price ?: 0).toString())
        )
    }

    override fun searchPlace(keyword: String): Call<SearchPlaceResponse> {
        return placeAPI.searchPlace(keyword)
    }

    override fun likePlace(placeIdx: Int): Call<BaseResponse> {
        return placeAPI.likePlace(LikePlaceRequest(placeIdx))
    }

    override fun unlikePlace(placeIdx: Int): Call<BaseResponse> {
        return placeAPI.unlikePlace(placeIdx)
    }
}