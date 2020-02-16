package com.jonilson.films.ui.viewmodel

import androidx.lifecycle.*
import com.jonilson.films.http.FilmHttp
import com.jonilson.films.model.FilmInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmListViewModel: ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun loadFilms () {
        if (state.value != null) return

        viewModelScope.launch {
            _state.value = State.StateLoading
            delay(3000)
            val result = withContext(Dispatchers.IO) {
                FilmHttp.searchFilm()
            }
            if (result?.items != null) {
                _state.value = State.StateLoaded(result.items)
            } else {
                _state.value = State.StateError(Exception("No results"), false)
            }
        }
    }

    sealed class State {
        object StateLoading: State()
        data class StateLoaded(val list: List<FilmInfo>): State()
        data class StateError(val error: Throwable, var hasConsumed: Boolean): State()
    }
}