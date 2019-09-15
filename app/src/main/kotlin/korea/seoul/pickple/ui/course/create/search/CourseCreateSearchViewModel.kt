package korea.seoul.pickple.ui.course.create.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.entity.Place

class CourseCreateSearchViewModel : ViewModel() {

    //region State
    val query : MutableLiveData<String> = MutableLiveData("")

    //endregion

    //region Data
    private val _places : MutableLiveData<List<Place>> = MutableLiveData(listOf(
        //TODO DUMMY
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null,null,null,999,"aaa"),
        Place(2,Place.Type.FOOD,"경복궁","경복경복",null,null,null,999,"aaa"),
        Place(3,Place.Type.FOOD,"경복궁","경복경복",null,null,null,999,"aaa"),
        Place(4,Place.Type.FOOD,"경복궁","경복경복",null,null,null,999,"aaa"),
        Place(5,Place.Type.FOOD,"경복궁","경복경복",null,null,null,999,"aaa"),
        Place(6,Place.Type.FOOD,"경복궁","경복경복",null,null,null,999,"aaa"),
        Place(7,Place.Type.FOOD,"경복궁","경복경복",null,null,null,999,"aaa")
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


    fun onClickBackButton() {
        _clickBack.value = Once(true)
    }

    fun onClickAddButton(place : Place) {
        _clickAdd.value = Once(place)
    }
}