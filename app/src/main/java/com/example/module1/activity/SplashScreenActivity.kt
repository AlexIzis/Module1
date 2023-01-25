package com.example.module1.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.module1.R
import com.example.module1.auth.ComposeLoginActivity
import com.example.module1.auth.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            //val intent = Intent(this, LoginActivity::class.java)
            val intent = Intent(this, ComposeLoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}
