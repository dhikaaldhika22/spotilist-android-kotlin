package com.myproject.app.spotilist.ui.songdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SongDetailViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    fun isFavorite(id: Int): Flow<Boolean> = favoriteRepository.isFavorite(id)

    fun saveToFavorite(music: MusicModel) {
        viewModelScope.launch {
            favoriteRepository.saveFavorite(music)
        }
    }

    fun deleteFavorite(music: MusicModel) {
        viewModelScope.launch {
            favoriteRepository.deleteFavorite(music)
        }
    }
}