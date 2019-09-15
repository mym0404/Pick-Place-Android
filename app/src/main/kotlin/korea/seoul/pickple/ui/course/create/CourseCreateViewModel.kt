package korea.seoul.pickple.ui.course.create

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place

class CourseCreateViewModel : ViewModel() {

    //region State
    private val _bottomExpanded: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val bottomExpanded: LiveData<Boolean>
        get() = _bottomExpanded


    val editMode = MediatorLiveData<Boolean>().apply {
        value = false

        addSource(bottomExpanded) {
            if(!it)
                this.value = false
        }
    }

    val detailMode : MutableLiveData<Boolean> = MutableLiveData(false)


    val mapRatio : MediatorLiveData<Float> = MediatorLiveData<Float>().apply {
        this.addSource(detailMode,Observer {detail->
            this.value = if(detail) 1.0f else (if(bottomExpanded.value == true) 0.00001f else 0.8f)
        })
        this.addSource(bottomExpanded, Observer {expanded->
            this.value = if(expanded) 0.00001f else 0.8f
        })

    }
    //endregion

    //region Data

    private val _places : MutableLiveData<List<Place>> = MutableLiveData(listOf(
        Place(1,Place.Type.FOOD,"사당역","경복경복",null,Location(37.4766,126.9816),null,999,"url"),
        Place(1,Place.Type.FOOD,"서울","경복경복",null,Location(37.5536,126.9696),null,999,"url")
    ))
    val places : LiveData<List<Place>>
        get() = _places

    val curPlace : MutableLiveData<Place?> = MutableLiveData(null)

    //endregion

    //region Event
    private val _clickPlaceAdd : MutableLiveData<Once<Boolean>> = MutableLiveData<Once<Boolean>>()
    val clickPlaceAdd : LiveData<Once<Boolean>>
        get() = _clickPlaceAdd

    private val _clickAllDelete : MutableLiveData<Once<Boolean>> = MutableLiveData<Once<Boolean>>()
    val clickAllDelete : LiveData<Once<Boolean>>
        get() = _clickAllDelete

    /**
     * For Sync with RecyclerViewAdapter because of ItemTouchHelper move
     */
    private val _syncData : MutableLiveData<Once<Boolean>> = MutableLiveData<Once<Boolean>>()
    val syncData : LiveData<Once<Boolean>>
        get() = _syncData

    private val _appendFailDuplicatePlace : MutableLiveData<Once<Place>> = MutableLiveData()
    val appendFailDuplicatePlace : LiveData<Once<Place>>
        get() = _appendFailDuplicatePlace
    //endregion


    private fun setDatas() {

    }

    fun onAppendPlace(place : Place) {
        _syncData.value = Once(true)

        //중복
        if((this.places.value ?: listOf()).any { it.id == place.id }) {
            _appendFailDuplicatePlace.value = Once(place)
            return
        }

        this._places.value = (this.places.value ?: listOf()) + listOf(place)
    }

    fun syncDataWith(items : List<Place>) {
        _places.value = items
    }


    //region Event
    fun onClickExpandButton() {
        _bottomExpanded.value = !(bottomExpanded.value!!)
    }

    fun onClickEditButton() {
        editMode.value = !(editMode.value!!)
    }

    fun onClickPlaceAddButton() {
        _clickPlaceAdd.value = Once(true)
    }

    fun onClickCourseSaveButton() {
        _syncData.value = Once(true)

    }


    fun onClickAllDeleteButton() {
        _clickAllDelete.value = Once(true)
    }
    fun allDelete() {
        _places.value = listOf()
    }

    //endregion

}

@BindingAdapter("app:ratio_animation")
fun View.setRatioWithAnimation(heightRatio : Float) {
    (this.layoutParams as? ConstraintLayout.LayoutParams)?.let { param ->

        val curHeightRatio = param.dimensionRatio.split(':')[1].toFloat()

        ValueAnimator.ofFloat(curHeightRatio, heightRatio).apply {
            addUpdateListener {
                val value = it.animatedValue as Float

                param.dimensionRatio = "1:$value"
                this@setRatioWithAnimation.layoutParams = param
            }

            duration = 500L

            start()
        }


    }
}

@BindingAdapter("app:rotation_animation")
fun View.setRotationWithAnimation(rotation: Float) {
    this.animate().rotation(rotation).apply {
        this.duration = 500L

        start()
    }
}
@BindingAdapter("android:visibility_animate")
fun View.setVisibilityBindingWithAnim(visible : Boolean) {
    this.animate().alpha(if(visible) 1f else 0f).apply {
        duration = 200L

        if(visible) this@setVisibilityBindingWithAnim.visibility = View.VISIBLE

        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                this@setVisibilityBindingWithAnim.visibility = if(visible) View.VISIBLE else View.GONE
            }
        })
    }

}
@BindingAdapter("android:visibility")
fun View.setVisibilityBinding(visible:Boolean) {
    this@setVisibilityBinding.visibility = if(visible) View.VISIBLE else View.GONE
}
