package com.jonilson.films.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film (
    @PrimaryKey
    val id: String,
    val image: String,
    val title: String,
    val director: String,
    val gender: String,
    val launch: String,
    val time: String,
    val synopsis: String
)