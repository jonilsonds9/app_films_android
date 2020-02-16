package com.jonilson.films.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jonilson.films.R
import com.jonilson.films.model.FilmInfo
import com.jonilson.films.repository.FilmRepository
import com.jonilson.films.ui.viewmodel.FilmDetailViewModel
import com.jonilson.films.ui.viewmodel.FilmVmFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_film_detail.*

class FilmDetailActivity : AppCompatActivity() {

    private val viewModel: FilmDetailViewModel by lazy {
        ViewModelProvider(
            this,
            FilmVmFactory(
                FilmRepository(this)
            )
        ).get(FilmDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)

        val film = intent.getParcelableExtra<FilmInfo?>(EXTRA_FILM)

        film?.run {
            Picasso.get()
                .load(film.image)
                .into(imgCover)
            txtTitle.text = film.title
            txtLaunch.text = film.launch
            txtDirector.text = film.director
            txtGender.text = film.gender
            txtTime.text = film.time
            txtSynopsis.text = film.synopsis

            viewModel.onCreate(film)
            viewModel.isFavote.observe(
                this@FilmDetailActivity,
                Observer { isFavorite ->
                    if (isFavorite) {
                        fabFavorite.setImageResource(R.drawable.ic_delete)
                        fabFavorite.setOnClickListener {
                            viewModel.removeFromFavorites(film)
                        }
                    } else {
                        fabFavorite.setImageResource(R.drawable.ic_add)
                        fabFavorite.setOnClickListener {
                            viewModel.saveToFavorite(film)
                        }
                    }

                })
        }
    }

    companion object {
        private const val EXTRA_FILM = "film"

        fun openWithVolume(context: Context, filmInfo: FilmInfo) {
            val intent = Intent(context, FilmDetailActivity::class.java)
            intent.putExtra(EXTRA_FILM, filmInfo)
            context.startActivity(intent)
        }
    }
}
