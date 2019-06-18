package com.captrip.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.captrip.list.TripListActivity
import com.captrip.presentation.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)

        if (savedInstanceState == null) {
            Handler().postDelayed({ startMainActivity() }, START_DELAY_MS)
        } else {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, TripListActivity::class.java))
        finish()
    }

    companion object {
        private const val START_DELAY_MS: Long = 1200
    }
}
