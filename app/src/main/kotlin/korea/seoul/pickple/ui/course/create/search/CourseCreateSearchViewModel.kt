package korea.seoul.pickple.ui.course.create.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.interfaces.PlaceRepository

class CourseCreateSearchViewModel(private val placeRepository: PlaceRepository) : ViewModel() {

    private val TAG = CourseCreateSearchViewModel::class.java.simpleName

    //region State
    val query : MutableLiveData<String> = MutableLiveData("")


    val filteredPlaces : MediatorLiveData<List<Place>> = MediatorLiveData<List<Place>>().apply {
        this.value = listOf()

        addSource(query) {
            if(query.value.isNullOrEmpty()) {
                this.value = places.value ?: listOf()
            }else {
                this.value = (places.value ?: listOf()).filter { it.name.contains(query.value!!) }
            }
        }
        addSource(places) {
            if(query.value.isNullOrEmpty()) {
                this.value = it
            }else {
                this.value = it.filter { it.name.contains(query.value!!) }
            }
        }

    }
    //endregion

    //region Data
    private val _places : MutableLiveData<List<Place>> = MutableLiveData(listOf(
    ))
    val places : LiveData<List<Place>>
        get() = _places
    //endregion

    //region Event

    private val _clickBack : MutableLiveData<Once<Boolean>> = MutableLiveData<Once<Boolean>>()
    val clickBack : LiveData<Once<Boolean>>
        get() = _clickBack

    private val _clickAdd : MutableLiveData<Once<Place>> = MutableLiveData()
    val clickAdd : LiveData<Once<Place>>
        get() = _clickAdd
    //endregion

    init {
        loadDatas()
    }

    private fun loadDatas() {
        placeRepository.searchPlace("")
            .callback({ it ->
                it.placeData?.run {
                    _places.value = this.map { it.toEntity() }
                }
            }, {
                Log.e(TAG,"Fail")
            }, {
                Log.e(TAG,it.toString())
            })
    }


    fun onClickBackButton() {
        _clickBack.value = Once(true)
    }

    fun onClickAddButton(place : Place) {
        _clickAdd.value = Once(place)
    }
}