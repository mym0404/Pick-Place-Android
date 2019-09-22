package korea.seoul.pickple.ui.navigation

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import korea.seoul.pickple.R
import korea.seoul.pickple.common.widget.Once
import korea.seoul.pickple.data.entity.Course

/**
 * Created by mj on 22, September, 2019
 */

class NavigationViewModel() : ViewModel() {

    private val TAG = NavigationViewModel::class.java.simpleName

    data class SeoulNews(
        @DrawableRes  val thumb : Int,
         val url : String
    )

    val seoulNews : MutableLiveData<List<SeoulNews>> = MutableLiveData(listOf(
        SeoulNews(R.drawable.seoul_news_dummy_1,"http://news.seoul.go.kr/gov/archives/508899"),
        SeoulNews(R.drawable.seoul_news_dummy_2,"http://news.seoul.go.kr/env/archives/503865"),
        SeoulNews(R.drawable.seoul_news_dummy_3,"http://news.seoul.go.kr/welfare/archives/509749"),
        SeoulNews(R.drawable.seoul_news_dummy_4,"http://news.seoul.go.kr/economy/archives/504737"),
        SeoulNews(R.drawable.seoul_news_dummy_5,"http://news.seoul.go.kr/env/archives/503823")
    ))
    val myCourse : MutableLiveData<List<Course>> = MutableLiveData(listOf())
    val pickPlace : MutableLiveData<List<Course>> = MutableLiveData(listOf())
    
    private val _clickSeoulNews : MutableLiveData<Once<SeoulNews>> = MutableLiveData()
    val clickSeoulNews : LiveData<Once<SeoulNews>>
        get() = _clickSeoulNews
    
    private val _clickCourse : MutableLiveData<Once<Course>> = MutableLiveData()
    val clickCourse : LiveData<Once<Course>>
        get() = _clickCourse


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

}
