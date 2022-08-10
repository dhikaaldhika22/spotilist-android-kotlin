package com.myproject.app.spotilist.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivitySplashScreenBinding
import com.myproject.app.spotilist.ui.MainActivity
import com.myproject.app.spotilist.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        initAction()
    }

    private fun initAction() {
        binding.apply {
            ivLogo.alpha = 0f
            ivLogo.animate().setDuration(2000).alpha(1f).withEndAction {
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}