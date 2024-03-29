package korea.seoul.pickple.ui.course.create

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.net.Uri
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.util.debugE
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.data.enumerator.SeoulDistrict
import korea.seoul.pickple.data.repository.interfaces.CourseRepository
import korea.seoul.pickple.data.repository.interfaces.DirectionsRepository
import korea.seoul.pickple.data.repository.interfaces.PlaceRepository
import kotlin.concurrent.thread

class CourseCreateViewModel(
    private val directionsRepository: DirectionsRepository,
    private val courseRepository: CourseRepository,
    private val placeRepository: PlaceRepository,
    private val mapKey : String,
     val title : String?,
    private val description : String?,
    private val tags : List<String>?,
    private val thumbnail : Uri?) : ViewModel() {

    private val TAG = CourseCreateViewModel::class.java.simpleName

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
    

    private val _onlyShow : MutableLiveData<Boolean> = MutableLiveData(false)
    val onlyShow : LiveData<Boolean>
        get() = _onlyShow

    private val _places : MutableLiveData<List<Place>> = MutableLiveData(listOf(
//        Place(1,Place.Type.FOOD,"사당역","경복경복",null,Location(37.4766,126.9816),null,999,"url"),
//        Place(1,Place.Type.FOOD,"서울","경복경복",null,Location(37.5536,126.9696),null,999,"url")
    ))
    val places : LiveData<List<Place>>
        get() = _places

    val curPlace : MutableLiveData<Place?> = MutableLiveData(null)

    //endregion

    //region Event
    private val _snackbarMsg : MutableLiveData<Once<String>> = MutableLiveData()
    val snackbarMsg : LiveData<Once<String>>
        get() = _snackbarMsg

    private val _courseCreateSuccess : MutableLiveData<Once<Unit>> = MutableLiveData()
    val courseCreateSuccess : LiveData<Once<Unit>>
        get() = _courseCreateSuccess
    
    private val _clickBackButton : MutableLiveData<Once<Boolean>> = MutableLiveData()
    val clickBackButton : LiveData<Once<Boolean>>
        get() = _clickBackButton

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

    private val _appendPlaceSuccess : MutableLiveData<Once<Place>> = MutableLiveData()
    val appendPlaceSuccess : LiveData<Once<Place>>
        get() = _appendPlaceSuccess

    private val _clickPlaceBackground : MutableLiveData<Once<Place>> = MutableLiveData()
    val clickPlaceBackground : LiveData<Once<Place>>
        get() = _clickPlaceBackground

    private val _clickPlaceDetail : MutableLiveData<Once<Place>> = MutableLiveData()
    val clickPlaceDetail : LiveData<Once<Place>>
        get() = _clickPlaceDetail
    //endregion




    fun onAppendPlace(place : Place) {
        //중복
        if((this.places.value ?: listOf()).any { it.id == place.id }) {
            _appendFailDuplicatePlace.value = Once(place)
            return
        }

        this._places.value = (this.places.value ?: listOf()) + listOf(place)
        _appendPlaceSuccess.value = Once(place)
    }

    fun syncDataWith(items : List<Place>) {
        _places.value = items
    }

    fun onSetDatas(onlyShow : Boolean, courseId : Int = -1) {
        _onlyShow.value = onlyShow

        if(onlyShow && courseId != -1) {


            courseRepository.getCourseInfo(courseId)
                .callback({
                    val course = it.data?.toEntity() ?: return@callback

                    thread {

                        try {
                            var places = listOf<Place>()

                            course.places?.forEach {
                                placeRepository.getPlace(it).execute().body()?.placeData?.toEntity()?.let {
                                    places += it
                                }

                            }

                            _places.postValue(places)
                        }catch(t : Throwable) {
                            debugE(TAG,t)
                        }
                    }
                }, {

                }, {

                })





        }
    }

    //region Event

    fun onClickPlaceDetail(place : Place) {
        _clickPlaceDetail.value = Once(place)
    }

    fun onClickMarble(position : Int) {
        try {
            curPlace.value = places.value?.getOrNull(position)
        }catch(t : Throwable) {

        }
    }

    fun onClickBackButton() {
        _clickBackButton.value = Once(true)
    }

    fun onClickExpandButton() {
        _bottomExpanded.value = !(bottomExpanded.value!!)
    }

    fun onClickEditButton() {
        editMode.value = !(editMode.value!!)
    }

    fun onClickPlaceAddButton() {
        _syncData.value = Once(true)
        _clickPlaceAdd.value = Once(true)
    }

    fun onClickCourseSaveButton() {
        _syncData.value = Once(true)

        if((places.value?.size ?: 0) < 2) {
            _snackbarMsg.value = Once("최소 두 개의 장소를 추가해야 합니다.")
            return
        }


        thread {

            try {

                val name = this.title ?: ""
                val description = this.description ?: ""
                val tagList = this.tags ?: listOf()

                var distances: List<Float> = listOf()

                repeat(places.value?.size?.minus(1) ?: 0) {
                    val p1 = places.value?.get(it)!!
                    val p2 = places.value?.get(it + 1)!!

                    val body = directionsRepository.getRouteFromTwoPlace(p1.location, p2.location, mapKey).execute().body()!!
                    val route = body.routes
                    val distanceMeter = route[0].legs[0].steps[0].distance.value
                    distances = distances + distanceMeter.toFloat()
                }

                courseRepository.createCourse(
                    name, description, thumbnail!!, places.value!!.map { it.id }, distances, tagList, SeoulDistrict.DONGJAK, Course.Type.CUSTOM
                ).callback({
                    _snackbarMsg.postValue(Once("코스 만들기 완료"))
                    _courseCreateSuccess.postValue(Once(Unit))
                }, {

                }, {

                })
            }catch(t : Throwable) {
                debugE(TAG,t)
            }
        }




    }

    fun onClickPlaceBackground(place : Place) {
        _clickPlaceBackground.value = Once(place)
    }

    fun onClickItemMoreButton(place : Place) {

    }
    fun onClickItemDeleteButton(place : Place) {
        _syncData.value = Once(true)

        _places.value = places.value!! - place
    }


    fun onClickAllDeleteButton() {
        _syncData.value = Once(true)
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
