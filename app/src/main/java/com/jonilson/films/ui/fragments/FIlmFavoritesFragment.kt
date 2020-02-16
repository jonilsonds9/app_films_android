package com.jonilson.films.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonilson.films.R
import com.jonilson.films.model.FilmInfo
import com.jonilson.films.repository.Film
import com.jonilson.films.repository.FilmRepository
import com.jonilson.films.ui.FilmDetailActivity
import com.jonilson.films.ui.adapter.FilmAdapter
import com.jonilson.films.ui.viewmodel.FilmFavoritesViewModel
import com.jonilson.films.ui.viewmodel.FilmVmFactory
import kotlinx.android.synthetic.main.fragment_film_list.*

class FIlmFavoritesFragment: Fragment() {

    private val viewModel: FilmFavoritesViewModel by lazy {
        ViewModelProvider(
            this,
            FilmVmFactory(
                FilmRepository(requireContext())
            )
        ).get(FilmFavoritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(
            R.layout.fragment_film_list,
            container,
            false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favoriteFilms.observe(
            viewLifecycleOwner,
            Observer {FilmInfoList ->
                val filmAdapter = FilmAdapter(FilmInfoList, this@FIlmFavoritesFragment::onFilmClick)
                rvFilms.layoutManager = LinearLayoutManager(requireContext())
                rvFilms.adapter = filmAdapter
            }
        )
    }

    private fun onFilmClick(filmInfo: FilmInfo) {
        FilmDetailActivity.openWithVolume(requireContext(), filmInfo)
    }
}