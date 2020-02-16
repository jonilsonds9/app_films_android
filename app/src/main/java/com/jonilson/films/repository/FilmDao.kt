package com.jonilson.films.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(film: Film)

    @Delete
    suspend fun delete(film: Film): Int

    @Query("SELECT * FROM Film")
    fun allFavorites(): Flow<List<Film>>

    @Query("SELECT COUNT(id) FROM Film WHERE id = :id")
    suspend fun isFavorite(id: String): Int
}