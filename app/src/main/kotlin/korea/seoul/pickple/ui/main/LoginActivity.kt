package korea.seoul.pickple.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.common.extensions.showSnackBar
import korea.seoul.pickple.common.util.SPUtil
import korea.seoul.pickple.data.repository.interfaces.UserRepository
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    private val userRepository: UserRepository by inject()
    private val spUtil: SPUtil by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        initView()
    }

    private fun initView() {
        spUtil.getString(SPUtil.SPKeys.LAST_LOGIN_EMAIL)?.let {
            emailEditText.setText(it)
        }
        spUtil.getString(SPUtil.SPKeys.LAST_LOGIN_PASSWORD)?.let {
            passwordEditText.setText(it)
        }
        if (!emailEditText.text.isNullOrEmpty() && !passwordEditText.text.isNullOrEmpty()) {
            //TODO 자동로그인 꺼놓음
//            signIn(emailEditText.text.toString(), passwordEditText.text.toString())
        }


        findPasswordText.setOnClickListener {
            Intent(this@LoginActivity, FindingPasswordActivity::class.java).apply {
                startActivity(this)
            }
        }
        signUpText.setOnClickListener {
            Intent(this@LoginActivity, SignUpActivity::class.java).apply {
                startActivity(this)
            }
        }

        loginButton.setOnClickListener {

            val email = emailEditText.text?.toString()
            val password = passwordEditText.text?.toString()

            if (email.isNullOrEmpty()) {
                emailText.error = "이메일을 채워주세요"
                return@setOnClickListener
            }
            if (password.isNullOrEmpty()) {
                passwordText.error = "패스워드를 채워주세요"
                return@setOnClickListener
            }
            if (!isValidEmail(email)) {
                emailText.error = "유효한 이메일 형식으로 채워주세요"
                return@setOnClickListener
            }

            signIn(email, password)


        }
    }

    private fun signIn(email: String, password: String) {

        loginProgressBar.visibility = View.VISIBLE

        userRepository.signIn(email, password) { success, message ->

            if (success) {
                runOnUiThread {

                    spUtil.putString(SPUtil.SPKeys.LAST_LOGIN_EMAIL, email)
                    spUtil.putString(SPUtil.SPKeys.LAST_LOGIN_PASSWORD, password)


                    Intent(this@LoginActivity, MainActivity::class.java).apply {
                        startActivity(this)
                        finish()
                    }
                }
            } else {
                runOnUiThread {
                    loginRootView.showSnackBar(message)
                }
            }

            runOnUiThread {
                loginProgressBar.visibility = View.GONE
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")

        return pattern.matcher(email).matches()
    }
}
