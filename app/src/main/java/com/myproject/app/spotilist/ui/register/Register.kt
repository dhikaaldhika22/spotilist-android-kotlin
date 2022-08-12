package com.myproject.app.spotilist.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.data.model.UserModel
import com.myproject.app.spotilist.databinding.ActivityRegisterBinding
import com.myproject.app.spotilist.ui.login.LoginActivity

class Register : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth

    private lateinit var database: DatabaseReference

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
            val username = tilUsername.text.toString()
            val email = tilEmail.text.toString()
            val password = tilPassword.text.toString()

            when {
                TextUtils.isEmpty(username) -> {
                    etUsername.error = "Username Can't be Empty!"
                    etUsername.requestFocus()
                }

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
                            database = FirebaseDatabase.getInstance("https://spotilist-c3825-default-rtdb.asia-southeast1.firebasedatabase.app")
                                .getReference("users")
                            val user = UserModel(tilUsername.text.toString())
                            database.child(task.result.user?.uid.orEmpty()).setValue(user.username)

                            if (task.isSuccessful) {
                                Toast.makeText(this@Register, "Your Account Registered Successfully", Toast.LENGTH_SHORT)
                                    .show()

                                val intent = Intent(this@Register, LoginActivity::class.java)
                                startActivity(intent)

                            } else {
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

}