package korea.seoul.pickple.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.showSnackBar
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.repository.interfaces.SetRepository
import kotlinx.android.synthetic.main.activity_setting_member_info_change_password.*
import org.koin.android.ext.android.inject

class SettingMemberInfoChangePasswordActivity : AppCompatActivity() {

    private val setRepository : SetRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_member_info_change_password)

        initView()
    }

    private fun initView() {

        backButton.setOnClickListener {
            onBackPressed()
        }

        okButton.setOnClickListener {

            val curPassword = editText1.text?.toString()
            val newPassword = editText2.text?.toString()
            val newPasswordCheck = editText2.text?.toString()

            if(curPassword.isNullOrEmpty()) {
                editText1Container.error = "기존 비밀번호를 입력해주세요"
            }else if(newPassword.isNullOrEmpty()) {
                editText2Container.error = "새로운 비밀번호를 입력해주세요"
            }else if(newPasswordCheck.isNullOrEmpty()) {
                editText3Container.error = "새로운 비밀번호를 입력해주세요"
            }else if(newPassword != newPasswordCheck) {
                rootContainer.showSnackBar("비밀번호 확인을 일치시켜주세요")
            }

            else {
                editText1Container.error = ""

                setRepository.changePassword(curPassword,newPassword)
                    .callback({
                        if(it.success) {

                            rootContainer.showSnackBar("성공적으로 변경되었습니다!")
                        }else {
                            rootContainer.showSnackBar(it.message)
                        }
                    }, {
                        rootContainer.showSnackBar("서버 오류")
                    }, {
                        rootContainer.showSnackBar("서버 오류")
                    })
            }
        }


    }
}
