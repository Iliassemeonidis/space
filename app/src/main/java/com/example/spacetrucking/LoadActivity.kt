package com.example.spacetrucking

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.spacetrucking.ui.base.MainActivity
import kotlinx.android.synthetic.main.load_activity.*

class LoadActivity : AppCompatActivity(R.layout.load_activity) {
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewLottieAnimation()
    }

    private fun viewLottieAnimation() {
            handler.postDelayed({
            startActivity(Intent(this@LoadActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}