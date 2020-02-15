package com.jonilson.films.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonilson.films.R
import com.jonilson.films.http.FilmHttp
import com.jonilson.films.model.FilmInfo
import com.jonilson.films.ui.adapter.FilmAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            progressLayout.visibility = View.VISIBLE
//            delay(2)
            val result = withContext(Dispatchers.IO) {
                FilmHttp.searchFilm()
            }
            progressLayout.visibility = View.GONE
            if (result?.items != null) {
                val filmAdapter = FilmAdapter(result.items, this@MainActivity::onFilmClick)
                rvFilms.layoutManager = LinearLayoutManager(this@MainActivity)
                rvFilms.adapter = filmAdapter
            } else {
                Toast.makeText(this@MainActivity,
                    R.string.erro_loading_films, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onFilmClick(film: FilmInfo) {
        val intencao = Intent(this, FilmDetailActivity::class.java)
        intencao.putExtra("film", film)
        startActivity(intencao)
    }
}
