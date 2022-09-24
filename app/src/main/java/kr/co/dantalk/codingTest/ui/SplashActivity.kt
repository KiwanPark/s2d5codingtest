package kr.co.dantalk.codingTest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kr.co.dantalk.codingTest.R
import kr.co.dantalk.codingTest.databinding.ActivitySplashBinding
import kr.co.dantalk.codingTest.ui.dialog.LottieDialog

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appLogo.setAnimation(R.raw.logo)

        object : CountDownTimer(4000L, 2000L) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }.start()
    }
}