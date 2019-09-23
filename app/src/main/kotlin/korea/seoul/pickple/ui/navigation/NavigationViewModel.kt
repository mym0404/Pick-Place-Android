package korea.seoul.pickple.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.entity.Course
import korea.seoul.pickple.data.entity.Review
import korea.seoul.pickple.data.entity.SeoulNews
import korea.seoul.pickple.data.repository.fake.FakeCourseRepository

/**
 * Created by mj on 22, September, 2019
 */

class NavigationViewModel() : ViewModel() {

    private val TAG = NavigationViewModel::class.java.simpleName



    val seoulNews : MutableLiveData<List<SeoulNews>> = MutableLiveData(listOf(
    ))
    val myCourse : MutableLiveData<List<Course>> = MutableLiveData(listOf(
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse

    ))
    val pickPlace : MutableLiveData<List<Course>> = MutableLiveData(listOf(
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse,
        FakeCourseRepository.fakeCourse
    ))
    val review : MutableLiveData<List<Review>> = MutableLiveData(listOf(
    ))
    
    private val _clickSeoulNews : MutableLiveData<Once<SeoulNews>> = MutableLiveData()
    val clickSeoulNews : LiveData<Once<SeoulNews>>
        get() = _clickSeoulNews
    
    private val _clickCourse : MutableLiveData<Once<Course>> = MutableLiveData()
    val clickCourse : LiveData<Once<Course>>
        get() = _clickCourse

    private val _clickReview : MutableLiveData<Once<Review>> = MutableLiveData()
    val clickReview : LiveData<Once<Review>>
        get() = _clickReview

    init {
        getDatas()
    }

    private fun getDatas() {

    }

    fun onClickSeoulNews(news : SeoulNews) {
        _clickSeoulNews.value = Once(news)
    }
    fun onClickCourse(course : Course) {
        _clickCourse.value = Once(course)
    }
    fun onClickRevieW(review : Review) {
        _clickReview.value = Once(review)
    }

}
