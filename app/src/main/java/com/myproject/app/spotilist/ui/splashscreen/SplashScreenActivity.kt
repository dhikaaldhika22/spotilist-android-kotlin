package com.myproject.app.spotilist.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.myproject.app.spotilist.databinding.ActivitySplashScreenBinding
import com.myproject.app.spotilist.ui.MainActivity
import com.myproject.app.spotilist.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        auth = Firebase.auth

        initAction()
    }

    private fun initAction() {
        binding.apply {
            ivLogo.alpha = 0f
            ivLogo.animate().setDuration(2000).alpha(1f).withEndAction {
                checkUser()
                finish()
            }
        }
    }

    private fun checkUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        } else {
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
        }
    }
}