package com.myproject.app.spotilist.data.repository

import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.data.room.SongsDao
import kotlinx.coroutines.flow.Flow

class FavoriteRepository private constructor(private val songsDao: SongsDao) {
    companion object {
        private val TAG = FavoriteRepository::class.java.simpleName
        private var INSTANCE: FavoriteRepository? = null

        fun getInstance(songsDao: SongsDao): FavoriteRepository {
            return INSTANCE ?: synchronized(this) {
                FavoriteRepository(songsDao).also {
                    INSTANCE = it
                }
            }
        }
    }

    fun isFavorite(id: Int): Flow<Boolean> {
        return songsDao.favoriteMusic(id)
    }

    fun getFavorite(): Flow<List<MusicModel>> {
        return songsDao.getMusic()
    }

    suspend fun saveFavorite(songs: MusicModel) {
        songsDao.insert(songs)
    }

    suspend fun deleteFavorite(songs: MusicModel)  {
        songsDao.deleteMusic(songs)
    }
}