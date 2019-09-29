package korea.seoul.pickple.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.databinding.ActivityOpenSourcesBinding

/**
 * Created by mj on 29, September, 2019
 */

class OpenSourcesActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityOpenSourcesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityOpenSourcesBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.lifecycleOwner = this


        mBinding.backButton.setOnClickListener {
            onBackPressed()
        }

        mBinding.text.text = ""
    }

}