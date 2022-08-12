package com.myproject.app.spotilist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MusicModel(
    val album: String = "",
    val duration: Double = 0.0,
    val imageUrl: String = "",
    val singers: String = "",
    val title: String = ""
) : Parcelable
