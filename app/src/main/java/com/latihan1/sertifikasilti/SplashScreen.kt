package com.latihan1.sertifikasilti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        LTI.alpha = 0f
        LTI.animate().setDuration(4000).alpha(1f).withEndAction{
            val i = Intent (this, MainActivity:: class.java)
        startActivity(i)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
        }
        LTI2.alpha = 0f
        LTI2.animate().setDuration(4000).alpha(1f).withEndAction{
            val i = Intent (this, MainActivity:: class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
