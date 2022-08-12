package com.myproject.app.spotilist.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityRegisterBinding
import com.myproject.app.spotilist.ui.login.LoginActivity

class Register : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()
        auth = Firebase.auth

        binding?.tvSignInMid?.setOnClickListener(this)
        binding?.btnSignUp?.setOnClickListener {
            createAccount()
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.tv_sign_in_mid -> startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun createAccount() {
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
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@Register) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                Toast.makeText(this@Register, "Your Account Registered Successfully", Toast.LENGTH_SHORT)
                                    .show()

                                val intent = Intent(this@Register, LoginActivity::class.java)
                                startActivity(intent)

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(this@Register, "Registration Failed Caused by: " + task.exception?.message,
                                    Toast.LENGTH_SHORT).show()
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