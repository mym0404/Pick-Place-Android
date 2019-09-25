package korea.seoul.pickple.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.data.repository.interfaces.SetRepository
import org.koin.android.ext.android.inject

class Setting_MemberInfoActivity : AppCompatActivity() {

    private val setRepository : SetRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting__member_info)

        initView()
    }

    private fun initView() {

    }
}
