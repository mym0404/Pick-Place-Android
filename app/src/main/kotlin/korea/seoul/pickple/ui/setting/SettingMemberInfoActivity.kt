package korea.seoul.pickple.ui.setting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.repository.interfaces.SetRepository
import kotlinx.android.synthetic.main.activity_setting.container1
import kotlinx.android.synthetic.main.activity_setting.container2
import kotlinx.android.synthetic.main.activity_setting.container3
import kotlinx.android.synthetic.main.activity_setting.email
import kotlinx.android.synthetic.main.activity_setting.iv_SetttingPage_close_btn
import kotlinx.android.synthetic.main.activity_setting_member_info.*
import org.koin.android.ext.android.inject

class SettingMemberInfoActivity : AppCompatActivity() {

    private val setRepository : SetRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_member_info)

        initView()
    }

    private fun initView() {
        iv_SetttingPage_close_btn.setOnClickListener {
            onBackPressed()
        }


        container1.setOnClickListener {
            Intent(this@SettingMemberInfoActivity,SettingMemberInfoEditNicknameActivity::class.java).apply{startActivity(this)}
        }
        container2.setOnClickListener {  }
        container3.setOnClickListener {
            Intent(this@SettingMemberInfoActivity,SettingMemberInfoChangePasswordActivity::class.java).apply{startActivity(this)}
        }

        setRepository.getUserInfo()
            .callback({
                it.data?.getOrNull(0)?.let {user->
                    email.text = user.email
                    nick.text = user.nickname
                }
            }, {

            }, {

            })
    }
}
