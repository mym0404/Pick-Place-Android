package korea.seoul.pickple.ui.course.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel(private val courseRepository: CourseRepository, course : Course) : ViewModel() {


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
        courseRepository.getPlaces(course).enqueue(object : Callback<List<Place>> {
            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                _loading.value = false
            }

            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                if(response.isSuccessful) {
                    _places.value = response.body()
                    _loading.value = false
                }else {
                    _loading.value = false
                }
            }
        })
    }
}