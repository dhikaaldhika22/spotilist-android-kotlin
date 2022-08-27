package com.myproject.app.spotilist.data.room

import androidx.room.*
import com.myproject.app.spotilist.data.model.MusicModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(componentEntity: MusicModel)

    @Query("SELECT * FROM music ORDER BY id ASC")
    fun getMusic() : Flow<List<MusicModel>>

    @Query("SELECT EXISTS(SELECT * FROM music WHERE id = :id AND favorite = 1)")
    fun favoriteMusic(id: Int): Flow<Boolean>

    @Delete
    suspend fun deleteMusic(musicModel: MusicModel)
}