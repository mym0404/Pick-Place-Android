package korea.seoul.pickple.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.showSnackBar
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.repository.interfaces.SetRepository
import korea.seoul.pickple.ui.login.TosActivity
import kotlinx.android.synthetic.main.activity_setting.*
import org.koin.android.ext.android.inject

class SettingActivity : AppCompatActivity() {


    private val setRepository: SetRepository by inject()

    private var isContactOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        initView()

    }

    private fun initView() {
        iv_SetttingPage_close_btn.setOnClickListener {
            onBackPressed()
        }

        contactOpenButton.setOnClickListener {
            if (isContactOpen) {
                contactOpenButton.rotation = 180f
                textField.visibility = View.GONE
                contactButton.visibility = View.GONE
            } else {
                contactOpenButton.rotation = 0f
                textField.visibility = View.VISIBLE
                contactButton.visibility = View.VISIBLE
            }
            isContactOpen = !isContactOpen
        }

        container1.setOnClickListener {
            Intent(this@SettingActivity, SettingMemberInfoActivity::class.java).apply { startActivity(this) }
        }
        container2.setOnClickListener {
            Intent(this@SettingActivity, TosActivity::class.java).apply { startActivity(this) }
        }
        container3.setOnClickListener {

        }
        container4.setOnClickListener {
            contactOpenButton.performClick()
        }

        try {
            version.text = packageManager.getPackageInfo(packageName, 0).versionName
        } catch (t: Throwable) {

        }

        setRepository.getUserInfo()
            .callback({
                it.data?.getOrNull(0)?.let {
                    email.text = it.email
                }
            }, {

            }, {

            })


        contactButton.setOnClickListener {

            val contact = contactEditText.text.toString()
            if (contact.isNullOrEmpty()) {
                settingRoot.showSnackBar("문의 사항을 입력해주세요")
                return@setOnClickListener
            } else {
                setRepository.emailContact(contact)
                    .callback({
                        if (it.success) {
                            settingRoot.showSnackBar("담당자에게 성공적으로 전달되었습니다")
                            contactEditText.setText("")
                        } else {
                            settingRoot.showSnackBar(it.message)
                        }
                    }, {
                        settingRoot.showSnackBar("서버 에러")
                    }, {
                        settingRoot.showSnackBar("서버 에러")
                    })
            }
        }
    }
}
