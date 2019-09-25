package korea.seoul.pickple.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import korea.seoul.pickple.R
import korea.seoul.pickple.data.repository.interfaces.UserRepository
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val userRepository : UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }
}
