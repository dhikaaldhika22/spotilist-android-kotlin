package com.myproject.app.spotilist.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myproject.app.spotilist.data.model.MusicModel

@Database(entities = [MusicModel::class], version = 1, exportSchema = false)
abstract class SongsDatabase : RoomDatabase() {

    abstract fun songsDao(): SongsDao

    companion object {
        private var INSTANCE: SongsDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): SongsDatabase {
            if (INSTANCE == null) {
                synchronized(SongsDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SongsDatabase::class.java, "favorite"
                    )
                        .build()
                }
            }
            return INSTANCE as SongsDatabase
        }
    }
}