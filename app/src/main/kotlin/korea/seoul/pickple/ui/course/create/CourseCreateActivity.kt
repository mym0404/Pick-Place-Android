package korea.seoul.pickple.ui.course.create

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.model.Marker
import korea.seoul.pickple.common.util.getPixelFromDP
import korea.seoul.pickple.common.widget.SimpleItemTouchHelperCallback
import korea.seoul.pickple.common.widget.observeOnce
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ActivityCourseCreateBinding
import korea.seoul.pickple.ui.course.create.search.CourseCreateSearchActivity
import korea.seoul.pickple.ui.navigation.NavigationArgs
import korea.seoul.pickple.ui.navigation.navigate
import korea.seoul.pickple.view.PickpleMapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference
import kotlin.math.sqrt

class CourseCreateActivity : AppCompatActivity() {

    private val TAG = CourseCreateActivity::class.java.simpleName

    private lateinit var mBinding: ActivityCourseCreateBinding

    private val mViewModel: CourseCreateViewModel by viewModel()

    private var mMapFragment: WeakReference<PickpleMapFragment?> = WeakReference(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCourseCreateBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)



        initMap()
        initRecyclerView()


        mBinding.lifecycleOwner = this
        mBinding.vm = this.mViewModel
        observeViewModel()

    }

    private fun initMap() {
        if (mMapFragment.get() == null) {
            val frag = PickpleMapFragment()

            mMapFragment = WeakReference(frag)
            this.supportFragmentManager.beginTransaction().add(mBinding.mapContainer.id, frag).commit()
        }

        mMapFragment.get()?.getController()?.run {
            //            setMyLocation()
            setZoom(10f)

            updateLocationAndZoomScale(mViewModel.places.value!!)

            setMarkerClickedListener { marker ->

                toLocation(marker)
                if(mViewModel.detailMode.value != true) {
                    mViewModel.detailMode.value = true
                }
            }
        }
    }

    private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            val adapter = CourseCreateAdapter(mViewModel,this@CourseCreateActivity)

            this.adapter = adapter

            ItemTouchHelper(SimpleItemTouchHelperCallback(adapter)).attachToRecyclerView(this)
        }

        mBinding.detailPager.apply {
            adapter = CourseCreateDetailAdapter()

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    mViewModel.curPlace.value = mViewModel.places.value!![position]
                    mBinding.pageIndicatorView.setSelected(position)
                }
            })
        }
    }

    private fun observeViewModel() {
        mViewModel.apply {

            places.observe(this@CourseCreateActivity, Observer { places ->
                mBinding.pageIndicatorView.count = places.size
                mMapFragment.get()?.getController()?.run {
                    updateLocationAndZoomScale(places, false)
                }

            })

            curPlace.observe(this@CourseCreateActivity, Observer { place ->

                if(place == null) return@Observer

                val idx = mViewModel.places.value!!.indexOf(place)

                if(idx != mBinding.detailPager.currentItem)
                    mBinding.detailPager.setCurrentItem(idx,true)

                toLocation(place)
            })

            detailMode.observe(this@CourseCreateActivity, Observer { detailMode ->

                val detailModeHeight = applicationContext.getPixelFromDP(56)
                val defaultHeight = applicationContext.getPixelFromDP(100)

                if (detailMode) {
                    ObjectAnimator.ofInt(mBinding.guideline,"guidelineBegin",defaultHeight,detailModeHeight).apply {
                        setAutoCancel(true)

                        duration = 500L
                        start()
                    }
                } else {
                    ObjectAnimator.ofInt(mBinding.guideline,"guidelineBegin",detailModeHeight,defaultHeight).apply {
                        setAutoCancel(true)

                        duration = 500L
                        start()
                    }
                }
            })

            clickPlaceAdd.observeOnce(this@CourseCreateActivity) {
                navigate(this@CourseCreateActivity,NavigationArgs.CourseCreateSearchArg(), CourseCreateSearchActivity.COURSE_SEARCH_REQUEST_CODE)
            }

            clickAllDelete.observeOnce(this@CourseCreateActivity) {
                AlertDialog.Builder(this@CourseCreateActivity)
                    .setTitle("장소 전체 삭제")
                    .setMessage("모든 장소를 삭제하시겠습니까?")
                    .setPositiveButton("예"){_,_ -> mViewModel.allDelete()}
                    .setNegativeButton("아니오"){_,_ ->}
                    .show()
            }


            syncData.observeOnce(this@CourseCreateActivity) {
                (mBinding.recyclerView.adapter as? CourseCreateAdapter)?.let {
                    mViewModel.syncDataWith(it.items)
                }
            }

        }
    }

    private fun toLocation(location: Location) {

    }

    private fun toLocation(place: Place) {
        place.location?.let { location ->
            mMapFragment.get()?.getController()?.setLocation(location,true)
        }
    }

    private fun toLocation(marker: Marker) {
        val places = mViewModel.places.value ?: return

        if (places.isEmpty()) return

        getNearestPlaceWithMarker(places, marker)?.let { place ->
            mViewModel.curPlace.value = place
            mMapFragment.get()?.getController()?.setLocation(place.location!!, true)
        }


    }

    private fun getNearestPlaceWithMarker(places: List<Place>, marker: Marker): Place? {
        return places.minBy {
            val lat = it.location?.latitude ?: 100.0
            val lng = it.location?.longitude ?: 100.0

            val markerLat = marker.position.latitude
            val markerLng = marker.position.longitude


            sqrt((lat - markerLat) * (lat - markerLat) + (lng - markerLng) * (lng - markerLng))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        when(requestCode) {
            CourseCreateSearchActivity.COURSE_SEARCH_REQUEST_CODE-> {
                when(resultCode) {
                    CourseCreateSearchActivity.COURSE_SEARCH_NONE_RESULT_CODE-> {

                    }
                    CourseCreateSearchActivity.COURSE_SEARCH_WITH_RESULT_CODE-> {

                        val selectedPlace = data?.getParcelableExtra<Place>(CourseCreateSearchActivity.EXTRA_SELECTED_PLACE_CODE)
                    }
                }
            }
        }

    }

    override fun onBackPressed() {

        if(mViewModel.detailMode.value == true) {
            mViewModel.detailMode.value = false
        }
        else if(mViewModel.bottomExpanded.value == true) {
            mViewModel.onClickExpandButton()
        }
        else {
            super.onBackPressed()
        }
    }
}