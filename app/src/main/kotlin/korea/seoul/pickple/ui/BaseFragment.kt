package korea.seoul.pickple.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<T: ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : Fragment() {
    protected lateinit var mBinding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }
}