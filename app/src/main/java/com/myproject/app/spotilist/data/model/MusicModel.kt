package com.myproject.app.spotilist.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "music", primaryKeys = ["id"])
@Parcelize
data class MusicModel(
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "album")
    val album: String = "",

    @ColumnInfo(name = "duration")
    val duration: Double = 0.0,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String = "",

    @ColumnInfo(name = "singer")
    val singers: String = "",

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "lyrics")
    var lyrics: String = "",

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) : Parcelable
