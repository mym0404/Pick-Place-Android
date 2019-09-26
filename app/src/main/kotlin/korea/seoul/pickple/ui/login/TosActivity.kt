package korea.seoul.pickple.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.databinding.ActivityTosBinding
import kotlinx.android.synthetic.main.activity_tos.*

/**
 * Created by mj on 26, September, 2019
 */

class TosActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityTosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTosBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)


        this.tosBackButton.setOnClickListener {
            onBackPressed()
        }
    }

}