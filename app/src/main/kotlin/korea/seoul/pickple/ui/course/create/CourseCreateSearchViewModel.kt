package korea.seoul.pickple.ui.course.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.widget.Once

class CourseCreateSearchViewModel : ViewModel() {

    private val _clickBack : MutableLiveData<Once<Boolean>> = MutableLiveData<Once<Boolean>>()
    val clickBack : LiveData<Once<Boolean>>
        get() = _clickBack



    fun onClickBackButton() {
        _clickBack.value = Once(true)
    }
}