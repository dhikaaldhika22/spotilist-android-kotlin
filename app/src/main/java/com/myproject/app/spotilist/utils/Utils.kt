package com.myproject.app.spotilist.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.myproject.app.spotilist.R

class Utils {
    companion object {
        fun ImageView.setImageGlide(context: Context, url: String) {
            Glide
                .with(context)
                .load(url)
                .into(this)
        }
    }
}
