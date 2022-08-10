package com.myproject.app.spotilist.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityLoginBinding
import com.myproject.app.spotilist.ui.Register

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.tvRegisterMid.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tv_register_mid -> startActivity(Intent(this, Register::class.java))
        }
    }


}