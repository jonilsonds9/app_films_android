package com.jonilson.films.repository

import com.jonilson.films.model.FilmInfo

object FilmMapper {
    fun FilmToFilmInfo(film: Film): FilmInfo {
        return FilmInfo(
            film.id,
            film.image,
            film.title,
            film.director,
            film.gender,
            film.launch,
            film.time,
            film.synopsis
        )
    }

    fun FilmInfoToFilm(filmInfo: FilmInfo): Film {
        return Film(
            filmInfo.id,
            filmInfo.image,
            filmInfo.title,
            filmInfo.director,
            filmInfo.gender,
            filmInfo.launch,
            filmInfo.time,
            filmInfo.synopsis
        )
    }
}