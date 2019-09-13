package korea.seoul.pickple.ui.course.create

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.model.Marker
import korea.seoul.pickple.common.widget.SimpleItemTouchHelperCallback
import korea.seoul.pickple.data.entity.Location
import korea.seoul.pickple.data.entity.Place
import korea.seoul.pickple.databinding.ActivityCourseCreateBinding
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
            val adapter = CourseCreateAdapter()

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
                if (detailMode) {
                    ObjectAnimator.ofFloat(mBinding.guideline,"guidelinePercent",0.14f,0.08f).apply {
                        setAutoCancel(true)

                        duration = 500L
                        start()
                    }
                } else {
                    ObjectAnimator.ofFloat(mBinding.guideline,"guidelinePercent",0.08f,0.14f).apply {
                        setAutoCancel(true)

                        duration = 500L
                        start()
                    }
                }
            })

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

    override fun onBackPressed() {

        if(mViewModel.detailMode.value == true) {
            mViewModel.detailMode.value = false
        }else {
            super.onBackPressed()
        }
    }
}