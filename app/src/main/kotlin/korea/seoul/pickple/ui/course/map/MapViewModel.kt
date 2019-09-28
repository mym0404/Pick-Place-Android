package korea.seoul.pickple.ui.course.map

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.util.debugE
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import korea.seoul.pickple.data.repository.interfaces.PlaceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Handler
import kotlin.concurrent.thread

class MapViewModel(private val courseRepository: CourseRepository,private val placeRepository: PlaceRepository, course : Course) : ViewModel() {


    private val TAG = MapViewModel::class.java.simpleName

    //region Datas
    private val _course : MutableLiveData<Course> = MutableLiveData<Course>(course)
    val course : LiveData<Course>
        get() = _course
    
    private val _places : MutableLiveData<List<Place>> = MutableLiveData<List<Place>>(listOf())
    val places : LiveData<List<Place>>
        get() = _places
    
    private val _loading : MutableLiveData<Boolean> = MutableLiveData<Boolean>(true)
    val loading : LiveData<Boolean>
        get() = _loading
    //endregion




    init {
        getPlacesForCourse(course)

    }

    private fun getPlacesForCourse(course : Course) {

        thread {

            var placeList = listOf<Place>()

            (course.places ?: listOf()).forEach {
                val response = placeRepository.getPlace(it).execute()
                response.body()?.placeData?.toEntity()?.let { placeList = placeList + it }
            }

            android.os.Handler(Looper.getMainLooper()).post { _places.value = placeList }
        }
    }
}