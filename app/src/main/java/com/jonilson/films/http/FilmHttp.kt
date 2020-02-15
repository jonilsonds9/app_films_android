package com.jonilson.films.http

import com.google.gson.Gson
import com.jonilson.films.model.SearchResult
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object FilmHttp {
    private const val FILMS_JSON_URL =
        "https://api-films-android.herokuapp.com/api/v1/films"

    private val client = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout( 30, TimeUnit.SECONDS)
        .build()

    fun searchFilm(): SearchResult? {
        val request = Request.Builder()
            .url(FILMS_JSON_URL)
            .build()
        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson<SearchResult>(
                json, SearchResult::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}