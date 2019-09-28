package korea.seoul.pickple.ui.course.intro.all_course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.ui.BaseViewModel

/**
 * 코스의 모든 장소정보를 보여주어야한다.
 * */
class ShowAllCoursesViewModel(
    places: List<Place>
) : BaseViewModel() {

    /**
    * 모든 course 데이터
    * */
    private val _livePlaces: MutableLiveData<List<Place>> = MutableLiveData()
    val livePlaces: LiveData<List<Place>> = _livePlaces

    /**
    * 사용자가 새로 누른 indexString
    * */
    private val _clickedIndex: MutableLiveData<Int> = MutableLiveData()
    val clickedIndex: LiveData<Int> = _clickedIndex

    init {
        _livePlaces.value = places
    }

    fun clickIndex(id: Int) {
        // 장소 index는 1 기반 인덱스이다.
        _clickedIndex.value = id + 1
    }
}