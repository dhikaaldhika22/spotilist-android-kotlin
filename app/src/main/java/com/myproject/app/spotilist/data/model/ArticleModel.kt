package com.myproject.app.spotilist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ArticleModel(
    val title: String = "",
    val imageUrl: String = "",
    val content: String = ""
) : Parcelable