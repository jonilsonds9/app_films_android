package com.jonilson.films.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmInfo (
    val image: String,
    val title: String,
    val director: String,
    val gender: String,
    val launch: String,
    val time: String,
    val synopsis: String
): Parcelable

