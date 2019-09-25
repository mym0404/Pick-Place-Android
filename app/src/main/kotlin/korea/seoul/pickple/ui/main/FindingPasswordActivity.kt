package korea.seoul.pickple.ui.main

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import korea.seoul.pickple.R
import kotlinx.android.synthetic.main.activity_finding_password.*

class FindingPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finding_password)

        m_btn_finding_pw.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.activity_finding_password__pop_up_, null)
//            val dialogText1 = dialogView.findViewById<TextView>(R.id.tv_finding_pw_alarm)
//            val dialogText2 = dialogView.findViewById<TextView>(R.id.tv_finding_pw_send)
//            val dialogText = dialogView.findViewById<EditText>(R.id.dialogEt)
//            val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

            builder.setView(dialogView)
                .setPositiveButton("OK") { dialogInterface, i ->

                }
                /*.setNegativeButton("취소") { dialogInterface, i ->
                    *//* 취소일 때 아무 액션이 없으므로 빈칸 *//*
                }*/
                .show()
        }
    }
}
