package com.myproject.app.spotilist.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    private val _favorite = MutableStateFlow(listOf<MusicModel>())
    val favorite = _favorite.asStateFlow()

    private fun getFavorites() {
        viewModelScope.launch {
            favoriteRepository.getFavorite().collect {
                _favorite.value = it
            }
        }
    }
}