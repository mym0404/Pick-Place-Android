package korea.seoul.pickple.common.widget

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import korea.seoul.pickple.common.util.IntentUtil
import korea.seoul.pickple.databinding.DialogShareBinding
import korea.seoul.pickple.ui.main.MainActivity
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt

/**
 * Created by mj on 26, September, 2019
 */


class ShareDialog : DialogFragment() {

    private val intentUtil : IntentUtil by inject()
    private var mBinding : DialogShareBinding by AutoClearedValue()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DialogShareBinding.inflate(inflater,container,false)
        mBinding.lifecycleOwner = viewLifecycleOwner

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.share.setOnClickListener {
            intentUtil.share(activity!!,"https://pickple.page.link/jTpt","코스를 공유하세요!")
        }
        mBinding.ok.setOnClickListener {
            Intent(activity!!,MainActivity::class.java).apply {
                this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(this)
            }
        }
    }
    
    /**
     * For Size
     */
    override fun onResume() {
        super.onResume()
    
        val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        wm.defaultDisplay.getSize(point)
        val width = (point.x * 0.85f).roundToInt()
        val height = (point.y * 0.7f).roundToInt()
        dialog?.window?.setLayout(width, height)
    }
}