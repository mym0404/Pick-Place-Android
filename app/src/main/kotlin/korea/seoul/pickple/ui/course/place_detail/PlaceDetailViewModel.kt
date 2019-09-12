package korea.seoul.pickple.ui.course.place_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.repository.PlaceRepository

class PlaceDetailViewModel(
    private val placeRepository: PlaceRepository,
    private val placeIds: List<Int>
) : ViewModel() {
    /**
     * 현재 선택된 Place
     * */
    private val _currentPlace: MutableLiveData<Place> = MutableLiveData()
    val currentPlace: LiveData<Place> = _currentPlace

    /**
     * Place의 캐시, 처음에 place는 null로 저장
     */
    private val placeList: MutableList<Place?> by lazy { MutableList<Place?>(placeIds.size) { null } }

    /**
    * Place들의 이미지 url 리스트
    * */
    private val _placeImageUrls: MutableLiveData<List<String>> = MutableLiveData()
    val placeImageUrls: LiveData<List<String>> = _placeImageUrls
    
    /**
    * 현재 선택한 Place의 index 1부터 시작한다.
    * */
    private val _index: MutableLiveData<Int> = MutableLiveData()
    val index: LiveData<Int> = _index

    init {
        // 인덱스가 변경되면, 현재 서버에 있는 place 정보를 받아온다.
        index.observeForever {
            if (it <= 0 || it >= placeList.size) return@observeForever
            if (placeList[it-1] != null) {
                _currentPlace.value = placeList[it-1]
            }
            else {
                placeRepository.getPlaceWithId(it-1)
                    .callback(
                        successCallback = { place ->
                            placeList[it-1] = place
                            _currentPlace.value = place
                        }
                    )
            }
        }

        placeIds.forEachIndexed { index, id ->
            placeRepository.getPlaceWithId(id)
                .callback (
                    successCallback = { place ->
                        placeList[index] = place
                        _placeImageUrls.value = placeList.map { it?.thumbnail?:"" }
                        _currentPlace.value = place
                    }
                )
        }

        _index.value = 1
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
}