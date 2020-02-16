package com.jonilson.films.repository

import android.content.Context
import com.jonilson.films.model.FilmInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepository(context: Context) {
    private val database = AppDatabase.getDatabase(context)

    suspend fun save(filmInfo: FilmInfo) {
        database.getFilmDao().save(
            FilmMapper.FilmInfoToFilm(filmInfo)
        )
    }

    suspend fun delete(filmInfo: FilmInfo) {
        database.getFilmDao().delete(
            FilmMapper.FilmInfoToFilm(filmInfo)
        )
    }

    suspend fun isFavorite(id: String): Boolean {
        return database.getFilmDao().isFavorite(id) > 0
    }

    fun allFavorites(): Flow<List<FilmInfo>> {
        return database.getFilmDao()
            .allFavorites()
            .map() { filmList ->
                filmList.map { film ->
                    FilmMapper.FilmToFilmInfo(film)
                }
            }
    }
}