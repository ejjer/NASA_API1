package com.example.nasa_api.view.ux

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.nasa_api.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<ImageView>(R.id.icon).animate().rotation(720f).setDuration(2000L).start()
        Handler(Looper.getMainLooper()).postDelayed({
           startActivity(Intent(this,UXActivity::class.java))
           finish()
       },2000L)
}
}