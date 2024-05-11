package com.example.sendmail

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.cleanBt.setOnClickListener {
            with (amb) {
                toEt.setText("")
                ccEt.setText("")
                bccEt.setText("")
                subjectEt.setText("")
                messageEt.setText("")
            }
        }

        amb.sendBt.setOnClickListener {
            val sendMailIntent = Intent(ACTION_SENDTO)

            with(sendMailIntent){
                putExtra(EXTRA_EMAIL, arrayOf(amb.toEt.text.toString()))
                putExtra(EXTRA_CC, arrayOf(amb.ccEt.text.toString()))
                putExtra(EXTRA_BCC, arrayOf(amb.bccEt.text.toString()))
                putExtra(EXTRA_SUBJECT, arrayOf(amb.subjectEt.text.toString()))
                putExtra(EXTRA_TEXT, arrayOf(amb.messageEt.text.toString()))
                type = "message/rfc822"
                data = Uri.parse("mailto:")
            }

            val chooserIntent = Intent(ACTION_CHOOSER)
            chooserIntent.putExtra(EXTRA_INTENT, sendMailIntent)
            startActivity(chooserIntent)
        }
    }
}