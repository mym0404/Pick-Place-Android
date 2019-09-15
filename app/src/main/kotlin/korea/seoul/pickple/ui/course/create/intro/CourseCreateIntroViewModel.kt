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
}