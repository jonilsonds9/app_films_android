package com.jonilson.films.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.jonilson.films.repository.FilmRepository

class FilmFavoritesViewModel(
    private val repository: FilmRepository
): ViewModel() {

    val favoriteFilms = repository.allFavorites().asLiveData()
}