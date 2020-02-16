package com.jonilson.films.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonilson.films.R
import com.jonilson.films.model.FilmInfo
import com.jonilson.films.ui.FilmDetailActivity
import com.jonilson.films.ui.adapter.FilmAdapter
import com.jonilson.films.ui.viewmodel.FilmListViewModel
import kotlinx.android.synthetic.main.fragment_film_list.*

class FilmListFragment: Fragment() {

    private val viewModel: FilmListViewModel by lazy {
        ViewModelProvider(this).get(FilmListViewModel::class.java)
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

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is FilmListViewModel.State.StateLoading -> {
                    progressLayout.visibility = View.VISIBLE
                }
                is FilmListViewModel.State.StateLoaded -> {
                    progressLayout.visibility = View.GONE

                    val filmAdapter = FilmAdapter(state.list, this@FilmListFragment::onFilmClick)
                    rvFilms.layoutManager = LinearLayoutManager(requireContext())
                    rvFilms.adapter = filmAdapter
                }
                is FilmListViewModel.State.StateError -> {
                    progressLayout.visibility = View.GONE

                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(requireContext(),
                            R.string.erro_loading_films, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        viewModel.loadFilms()
    }

    private fun onFilmClick(film: FilmInfo) {
        FilmDetailActivity.openWithVolume(requireContext(), film)
    }
}