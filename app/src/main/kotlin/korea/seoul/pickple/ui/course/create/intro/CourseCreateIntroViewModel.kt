package korea.seoul.pickple.ui.course.create.intro

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.widget.Once

class CourseCreateIntroViewModel : ViewModel() {

    val title : MutableLiveData<String> = MutableLiveData("")

    private val _thumbnailUri : MutableLiveData<Uri?> = MutableLiveData(null)
    val thumbnailUri : LiveData<Uri?>
        get() = _thumbnailUri

    val courseDescription : MutableLiveData<String> = MutableLiveData("")

    val courseTagString : MutableLiveData<String> = MutableLiveData("")

    //region Event
    private val _clickBackButton : MutableLiveData<Once<Boolean>> = MutableLiveData()
    val clickBackButton : LiveData<Once<Boolean>>
        get() = _clickBackButton

    private val _clickImageAdd : MutableLiveData<Once<Boolean>> = MutableLiveData()
    val clickImageAdd : LiveData<Once<Boolean>>
        get() = _clickImageAdd
    
    private val _clickPlaceAdd : MutableLiveData<Once<Pair<Pair<String,Uri>,Pair<String,String>>>> = MutableLiveData()
    val clickPlaceAdd : LiveData<Once<Pair<Pair<String,Uri>,Pair<String,String>>>>
        get() = _clickPlaceAdd


    private val _toastMsg : MutableLiveData<Once<String>> = MutableLiveData()
    val toastMsg : LiveData<Once<String>>
        get() = _toastMsg
    //endregion


    fun onImageUriChanged(uri : Uri) {
        _thumbnailUri.value = uri
    }

    fun onClickBackButton() {
        _clickBackButton.value = Once(true)
    }

    fun onClickAddThumbnail() {
        _clickImageAdd.value = Once(true)
    }

    fun onClickPlaceAddButton() {
        if(title.value.isNullOrEmpty()) {
            _toastMsg.value = Once("코스의 제목을 입력해주세요.")
            return
        }
        else if(thumbnailUri.value == null) {
            _toastMsg.value = Once("썸네일 사진을 등록해주세요.")
            return
        }
        else if(courseDescription.value.isNullOrEmpty()) {
            _toastMsg.value = Once("간단한 코스 설명을 입력해주세요.")
            return
        }else if(courseTagString.value.isNullOrEmpty()) {
            _toastMsg.value = Once("태그를 1개이상 등록해주세요.")
            return
        }

        _clickPlaceAdd.value = Once(
            (title.value!! to thumbnailUri.value!!) to (courseDescription.value!! to courseTagString.value!!)
        )
    }
}