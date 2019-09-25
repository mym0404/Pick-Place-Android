package korea.seoul.pickple.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var checkValid_id: Boolean = false
        var chechkValid_nickname: Boolean = false
        var checkValid_pw: Boolean = false
        var checkValid_pwCheck: Boolean = false

        tiet_signup_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val emailPattern = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
                if (!Pattern.matches(emailPattern, s.toString()))
                    tv_signup_email_wrong.setText("잘못된 형식")
                else {
                    tv_signup_email_wrong.setText(null)
                    checkValid_id = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (tiet_signup_email.text.toString() != "" && tiet_signup_pw.text.toString() != "" && tiet_signup_pw_check.text.toString() != "") {
                    m_btn_signup.isClickable = true
                    //btn_submitSignup.setImageResource(R.drawable.btn_login_orange)
                } else {
                    // btn_submitSignup.setImageResource(R.drawable.btn_login_gray)
                    m_btn_signup.isClickable = false
                }
            }
        })

        //******닉네임******
//        tiet_signup_nickname.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                if (tiet_signup_nickname.length())
//            }
//        }
//    })

        tiet_signup_pw_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (tiet_signup_pw_check.text.toString().equals(tiet_signup_pw_check.text.toString())) {
                    tv_singup_pw_check_wrong.setText(null)
                    checkValid_pwCheck = true
                } else
                    tv_singup_pw_check_wrong.setText("불일치")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (tiet_signup_email.text.toString() != "" && tiet_signup_pw.text.toString() != "" && tiet_signup_pw_check.text.toString() != "") {
                    //btn_submitSignup.setImageResource(R.drawable.btn_login_orange)
                    m_btn_signup.isClickable = true
                } else {
                    //btn_submitSignup.setImageResource(R.drawable.btn_login_gray)
                    m_btn_signup.isClickable = false
                }
            }
        })


//    fun isValidRegistrationID(id: String): Boolean {
//        //if(id.length != 13) return false
//        val reg = Regex("^[a-zA-Z0-9_!#\$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
//        if(!id.matches(reg)) return false
//
////        var tempSum= 0
////        for (i in 0 until id.length - 1) {
////            tempSum += id[i].toString().toInt() * ((i % 8) + 2)
////        }
//
//        return 11 - (tempSum % 11) == id[id.length - 1].toString().toInt()
//    }

//    private fun afterTextChanged() {
//        if (tiet_signup_email==)
//    }
    }
    }