package korea.seoul.pickple.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.activity_setting_member_info_change_password.*

class SettingMemberInfoChangePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_member_info_change_password)

        initView()
    }

    private fun initView() {
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
