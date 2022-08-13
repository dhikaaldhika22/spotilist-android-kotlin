@file:Suppress("DEPRECATION")

package com.myproject.app.spotilist.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityLoginBinding
import com.myproject.app.spotilist.ui.MainActivity
import com.myproject.app.spotilist.ui.register.Register


class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() =_binding

    private lateinit var auth: FirebaseAuth
    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()
        auth = Firebase.auth
        createRequest()

        binding?.tvRegisterMid?.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }

        binding?.btnSignIn?.setOnClickListener {
            loginUser()
        }

        binding?.btnGoogle?.setOnClickListener {
            signInGoogle()
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
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Login Failed caused by: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun createRequest() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signInGoogle() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            if (task.isSuccessful) {
                try {
                    val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account)
                } catch (e: ApiException) {

                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        auth.currentUser!!
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Sorry auth failed.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    companion object {
        private const val RC_SIGN_IN = 123
    }
}