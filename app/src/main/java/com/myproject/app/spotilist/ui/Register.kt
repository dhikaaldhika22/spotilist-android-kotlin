package com.myproject.app.spotilist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityRegisterBinding
import com.myproject.app.spotilist.ui.login.LoginActivity

class Register : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvSignInMid.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tv_sign_in_mid -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}