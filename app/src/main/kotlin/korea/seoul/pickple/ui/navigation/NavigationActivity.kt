package korea.seoul.pickple.ui.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        setClickListener()
    }

    private fun setClickListener() {
        act_nav_ibtn_close.setOnClickListener{
            finish()
        }
    }
}
