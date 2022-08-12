package com.myproject.app.spotilist.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityLoginBinding
import com.myproject.app.spotilist.ui.MainActivity
import com.myproject.app.spotilist.ui.register.Register

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() =_binding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()
        auth = Firebase.auth

        binding?.tvRegisterMid?.setOnClickListener(this)
        binding?.btnSignIn?.setOnClickListener {
            loginUser()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.tv_register_mid -> startActivity(Intent(this, Register::class.java))
        }
    }

    private fun loginUser() {
        binding?.apply {
            val email = tilEmail.text.toString()
            val password = tilPassword.text.toString()

            when {
                TextUtils.isEmpty(email) -> {
                    etEmail.error = "Email Can't be Empty!"
                    etEmail.requestFocus()
                }
                TextUtils.isEmpty(password) -> {
                    etPassword.error = "Password Can't be Empty"
                    etPassword.requestFocus()
                }
                else -> {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this@LoginActivity) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@LoginActivity, "Login Failed caused by: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    companion object {
        private const val TAG = "EmailPassword"
    }

}