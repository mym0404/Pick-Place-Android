package korea.seoul.pickple.data.api

import korea.seoul.pickple.data.api.response.main.MainListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


interface MainAPI {
    @GET("main/list/{type}")
    fun listMainCourses(@Path("type") type : Int) : Call<MainListResponse>


    @GET("main/order")
    fun listMainCoursesWithPopularOrder() : Call<MainListResponse>
}