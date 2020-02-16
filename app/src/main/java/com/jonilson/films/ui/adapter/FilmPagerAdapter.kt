package com.jonilson.films.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jonilson.films.ui.fragments.FIlmFavoritesFragment
import com.jonilson.films.ui.fragments.FilmListFragment

class FilmPagerAdapter(fa: FragmentActivity):
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FilmListFragment()
            else -> FIlmFavoritesFragment()
        }
}