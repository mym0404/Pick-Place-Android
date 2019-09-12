package korea.seoul.pickple.ui.course.create

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.widget.CubicBezierInterpolator
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place

class CourseCreateViewModel : ViewModel() {

    //region State
    private val _bottomExpanded: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val bottomExpanded: LiveData<Boolean>
        get() = _bottomExpanded
    
    private val _editMode : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val editMode : LiveData<Boolean>
        get() = _editMode
    //endregion

    //region Data

    private val _places : MutableLiveData<List<Place>> = MutableLiveData<List<Place>>(listOf(
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null,Location(1.0,1.0),null,999,"url"),
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null,Location(1.0,1.0),null,999,"url"),
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null,Location(1.0,1.0),null,999,"url"),
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null, Location(1.0,1.0),null,999,"url"),
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null,Location(1.0,1.0),null,999,"url"),
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null,Location(1.0,1.0),null,999,"url"),
        Place(1,Place.Type.FOOD,"경복궁","경복경복",null,Location(1.0,1.0),null,999,"url")
    ))
    val places : LiveData<List<Place>>
        get() = _places
    
    //endregion

    //region Event

    //endregion


    //region Event
    fun onClickExpandButton() {
        _bottomExpanded.value = !(bottomExpanded.value!!)
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
        this.duration = 300L

        interpolator = CubicBezierInterpolator(0f,0f,1f,1f)
        start()
    }
}
@BindingAdapter("android:visibility")
fun View.setVisibilityBinding(visible : Boolean) {
    this.animate().alpha(if(visible) 1f else 0f).apply {
        duration = 500L

        if(visible) this@setVisibilityBinding.visibility = View.VISIBLE

        setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                this@setVisibilityBinding.visibility = if(visible) View.VISIBLE else View.GONE
            }
        })
    }

}