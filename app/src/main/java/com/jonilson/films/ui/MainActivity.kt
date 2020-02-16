package com.jonilson.films.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.jonilson.films.R
import com.jonilson.films.ui.adapter.FilmPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager.adapter = FilmPagerAdapter(this)
        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.setText(
                if (position == 0) {
                    R.string.tab_films
                } else {
                    R.string.tab_favorites
                }
            )
        }.attach()
    }
}
