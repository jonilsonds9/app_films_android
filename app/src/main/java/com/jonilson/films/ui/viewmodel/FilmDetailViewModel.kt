package com.jonilson.films.ui.viewmodel

import androidx.lifecycle.*
import com.jonilson.films.model.FilmInfo
import com.jonilson.films.repository.FilmRepository
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val repository: FilmRepository
): ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavote: LiveData<Boolean> = _isFavorite

    fun onCreate(filmInfo: FilmInfo) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(filmInfo.id)
        }
    }

    fun saveToFavorite(filmInfo: FilmInfo) {
        viewModelScope.launch {
            repository.save(filmInfo)
            _isFavorite.value = repository.isFavorite(filmInfo.id)
        }
    }

    fun removeFromFavorites(filmInfo: FilmInfo) {
        viewModelScope.launch {
            repository.delete(filmInfo)
            _isFavorite.value = repository.isFavorite(filmInfo.id)
        }
    }
}