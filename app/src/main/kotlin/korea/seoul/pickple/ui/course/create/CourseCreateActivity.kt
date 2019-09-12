package korea.seoul.pickple.ui.course.create

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.databinding.ActivityCourseCreateBinding
import korea.seoul.pickple.view.PickpleMapFragment
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class CourseCreateActivity : AppCompatActivity() {

    private val TAG = CourseCreateActivity::class.java.simpleName

    private lateinit var mBinding : ActivityCourseCreateBinding

    private val mViewModel : CourseCreateViewModel by viewModel()

    private var mMapFragment: WeakReference<PickpleMapFragment?> = WeakReference(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCourseCreateBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.lifecycleOwner = this
        mBinding.vm = this.mViewModel

        initMap()
        initRecyclerView()
        observeViewModel()

    }
    private fun initMap() {
        if (mMapFragment.get() == null) {
            val frag = PickpleMapFragment()

            mMapFragment = WeakReference(frag)
            this.supportFragmentManager.beginTransaction().add(mBinding.mapContainer.id, frag).commit()
        }

        mMapFragment.get()?.getController()?.
    }
    private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            adapter = CourseCreateAdapter()
        }
    }

    private fun observeViewModel() {
        mViewModel.apply {

        }
    }
}