package korea.seoul.pickple.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.showSnackBar
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.data.repository.interfaces.UserRepository
import kotlinx.android.synthetic.main.activity_finding_password.*
import org.koin.android.ext.android.inject

class FindingPasswordActivity : AppCompatActivity() {

    private val userRepository : UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finding_password)

        initView()
    }

    private fun initView() {
        findPasswordCloseButton.setOnClickListener {
            onBackPressed()
        }

        m_btn_finding_pw.setOnClickListener {
            val email = emailEditTextFindingPassword.text.toString() ?: ""

            userRepository.findPassword(email)
                .callback({
                    runOnUiThread {
                        if (it.success) {
                            findPasswordRootView.showSnackBar("패스워드 찾기 이메일을 전송했습니다")
                        } else {
                            findPasswordRootView.showSnackBar(it.message)
                        }
                    }
                }, {
                    runOnUiThread {
                        findPasswordRootView.showSnackBar("서버 오류")
                    }
                }, {
                    runOnUiThread {
                        findPasswordRootView.showSnackBar("서버 오류")
                    }
                })

        }
    }
}
