package com.myproject.app.spotilist.di

import android.content.Context
import com.myproject.app.spotilist.data.repository.FavoriteRepository
import com.myproject.app.spotilist.data.room.SongsDatabase

object Injection {
    fun provideRepo(context: Context): FavoriteRepository {
        val db = SongsDatabase.getDatabase(context)
        val songsDao = db.songsDao()

        return FavoriteRepository.getInstance(songsDao)
    }
}
