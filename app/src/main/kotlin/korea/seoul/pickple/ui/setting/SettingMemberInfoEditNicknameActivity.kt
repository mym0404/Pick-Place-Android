package korea.seoul.pickple.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.showSnackBar
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.repository.interfaces.SetRepository
import kotlinx.android.synthetic.main.activity_setting_member_info_edit_nickname.*
import org.koin.android.ext.android.inject

class SettingMemberInfoEditNicknameActivity : AppCompatActivity() {

    private val setRepository : SetRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_member_info_edit_nickname)

        initView()
    }

    private fun initView() {
        setRepository.getUserInfo()
            .callback({
                it.data?.getOrNull(0)?.let { user ->
                    editText1.setText(user.nickname)
                }
            }, {

            }, {

            })

        backButton.setOnClickListener {
            onBackPressed()
        }

        okButton.setOnClickListener {

            val nickname = editText1.text?.toString()
            if(nickname.isNullOrEmpty()) {
                editText1Container.error = "닉네임을 입력해주세요"
            }else {
                editText1Container.error = ""

                setRepository.changeNickname(nickname)
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
