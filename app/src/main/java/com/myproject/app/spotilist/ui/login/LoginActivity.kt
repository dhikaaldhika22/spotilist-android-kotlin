package com.myproject.app.spotilist.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myproject.app.spotilist.R

abstract class LoginActivity : AppCompatActivity(), View.OnTouchListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

}