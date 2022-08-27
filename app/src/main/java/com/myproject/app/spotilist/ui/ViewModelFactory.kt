package com.myproject.app.spotilist.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myproject.app.spotilist.data.repository.FavoriteRepository
import com.myproject.app.spotilist.di.Injection
import com.myproject.app.spotilist.ui.favorite.FavoriteViewModel
import com.myproject.app.spotilist.ui.songdetail.SongDetailViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val favoriteRepository: FavoriteRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SongDetailViewModel::class.java) -> {
                SongDetailViewModel(favoriteRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(favoriteRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepo(context)).also {
                    INSTANCE = it
                }
            }
        }
    }

}