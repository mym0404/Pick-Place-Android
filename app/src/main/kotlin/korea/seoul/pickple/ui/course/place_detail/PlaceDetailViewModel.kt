package korea.seoul.pickple.ui.course.place_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.PlaceRepository

class PlaceDetailViewModel(
    placeRepository: PlaceRepository,
    placeIds: List<Int>
) : ViewModel() {
    private val placeRepository = placeRepository
    var placeIds: List<Int> = placeIds
        set(value) {
            // place 캐시 새로만들기.
            makePlaceCache(value)
            // place들 이미지 url 리스트 새로만들기.
            makePlaceImageUrlList(value)
            // index 초기화
            _index.value = 1
            field = value
        }

    /**
     * 현재 선택된 Place
     * */
    private val _currentPlace: MutableLiveData<Place> = MutableLiveData()
    val currentPlace: LiveData<Place> = _currentPlace

    /**
     * Place의 캐시, 처음에 place는 null로 저장
     */
    private var placeList: MutableList<Place?> = mutableListOf()

    /**
    * Place들의 이미지 url 리스트
    * */
    private val _placeImageUrls: MutableLiveData<List<String>> = MutableLiveData()
    val placeImageUrls: LiveData<List<String>> = _placeImageUrls
    
    /**
    * 현재 선택한 Place의 index 1부터 시작한다.
    * */
    private val _index: MutableLiveData<Int> = MutableLiveData()
    val index: LiveData<String> = Transformations.map(_index) {
        if (it>10) it.toString() else "0$it"
    } // 한 자리 index는 0을 붙혀서 보여준다.

    init {
        // 인덱스가 변경되면, 현재 서버에 있는 place 정보를 받아온다.
        _index.observeForever {
            if (it < 1 || it > placeList.size) return@observeForever
            if (placeList[it-1] != null) {
                _currentPlace.value = placeList[it-1]
            }
            else {
                // 데이터 로드가 안되있으면 로드한다.
                placeRepository.getPlaceWithId(it-1)
                    .callback(
                        successCallback = { place ->
                            placeList[it-1] = place
                            _currentPlace.value = place
                        }
                    )
            }
        }
    }

    /**
     * 사용자가 좋아요 버튼을 누르는 이벤트 발생
     * */
    fun clickLikeButton() {
    
    }

    /**
     * 사용자가 후기 남기기 버튼을 누르는 이벤트 발생
     * */
    fun clickPostComment() {

    }

    /**
     * 사용자가 이미지를 스크롤해서 특정 index의 Place를 고름
     * index 는 1-based index이다!
     * @param index 1-based index
     * */
    fun selectPlace(index: Int) {
        _index.value = index
    }

    private fun makePlaceCache(placeIds: List<Int>) {
        placeList = MutableList(placeIds.size) { null }
    }

    private fun makePlaceImageUrlList(placeIds: List<Int>) {
        // id를 기반으로 실제 데이터를 받아온다.
        placeIds.forEachIndexed { index, id ->
            placeRepository.getPlaceWithId(id)
                .callback (
                    successCallback = { place ->
                        placeList[index] = place
                        _placeImageUrls.value = placeList.map { it?.thumbnail?:"" }
                    }
                )
        }
    }
}