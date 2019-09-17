package korea.seoul.pickple.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        iv_setting_contact_btn.setOnClickListener {
            mt_setting_ToContact.visibility = View.VISIBLE
            rl_setting_ToContact_btn.visibility = View.VISIBLE

        }
    }
}
