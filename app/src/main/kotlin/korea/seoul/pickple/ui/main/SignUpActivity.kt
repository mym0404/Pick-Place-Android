package korea.seoul.pickple.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.showSnackBar
import korea.seoul.pickple.common.util.callback
import korea.seoul.pickple.common.widget.AsteriskPasswordTransformationMethod
import korea.seoul.pickple.data.repository.interfaces.UserRepository
import korea.seoul.pickple.ui.login.TosActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.ext.android.inject
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private val userRepeatable: UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var checkValid_id: Boolean = false
        var chechkValid_nickname: Boolean = false
        var checkValid_pw: Boolean = false
        var checkValid_pwCheck: Boolean = false

        tiet_signup_pw.transformationMethod = AsteriskPasswordTransformationMethod()
        tiet_signup_pw_check.transformationMethod = AsteriskPasswordTransformationMethod()

        tiet_signup_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isValidEmail(tiet_signup_email.text?.toString() ?: "")) {
                    tv_signup_email_wrong.setText(null)
                    checkValid_id = true
                }
                else {
                    tv_signup_email_wrong.setText("잘못된 형식")
                    checkValid_id = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                m_btn_signup.isClickable = tiet_signup_email.text.toString() != "" && tiet_signup_pw.text.toString() != "" && tiet_signup_pw_check.text.toString() != ""
            }
        })

        tiet_signup_nickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (tiet_signup_nickname.text?.length!! > 10) {
                    tv_signup_nickname_wrong.setText("잘못된 형식")
                    chechkValid_nickname = false
                }
                else {
                    tv_signup_nickname_wrong.setText(null)
                    chechkValid_nickname = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        tiet_signup_pw_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (tiet_signup_pw_check.text.toString().equals(tiet_signup_pw.text.toString())) {
                    tv_singup_pw_check_wrong.setText(null)
                    checkValid_pwCheck = true
                } else
                    tv_singup_pw_check_wrong.setText("불일치")
                    checkValid_pwCheck = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                m_btn_signup.isClickable = tiet_signup_email.text.toString() != "" && tiet_signup_pw.text.toString() != "" && tiet_signup_pw_check.text.toString() != ""
            }
        })

        tv_MemberTermsConfrim.setOnClickListener {
            Intent(this@SignUpActivity, TosActivity::class.java).apply { startActivity(this) }
        }

        m_btn_signup.setOnClickListener {
            signupFunction(tiet_signup_email.text.toString(), tiet_signup_nickname.text.toString(), tiet_signup_pw.text.toString())
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")

        return pattern.matcher(email).matches()
    }

    private fun signupFunction(email : String, nickname : String, pw : String) {
        userRepeatable.signUp(email, nickname, pw).callback(
            {
                signup_relative.showSnackBar(it.message)

                if (it.success) {
                    finish()
                }
            }, {

            }, {

            }
        )
    }
}