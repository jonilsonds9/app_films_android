package com.jonilson.films.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.jonilson.films.R
import com.jonilson.films.model.FilmInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_film_detail.*

class FilmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        val film = intent.getParcelableExtra<FilmInfo?>("film")

        if (film != null) {
            Picasso.get()
                .load(film.image)
                .into(imgCover)
            txtTitle.text = film.title
            txtLaunch.text = film.launch
            txtGender.text = film.gender
            txtDirector.text = film.director
            txtTime.text = film.time
            txtSynopsis.text = film.synopsis
        } else {
            Toast.makeText(this@FilmDetailActivity, "Erro ao carregar!", Toast.LENGTH_LONG).show()
        }
    }
}
