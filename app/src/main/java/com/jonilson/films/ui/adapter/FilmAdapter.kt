package com.jonilson.films.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jonilson.films.R
import com.jonilson.films.model.FilmInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(
    private val list: List<FilmInfo>,
    private val itemCallback: (FilmInfo) -> Unit
): RecyclerView.Adapter<FilmAdapter.FilmVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return FilmVH(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FilmVH, position: Int) {
        val film = list[position]
        holder.run {
            Picasso.get()
                .load(film.image)
                .into(imgCover)
            txtTitle.text = film.title
            txtLaunch.text = film.launch
            txtGender.text = film.gender
            itemView.setOnClickListener {
                itemCallback(film)
            }
        }
    }

    inner class FilmVH(parent: View)
        : RecyclerView.ViewHolder(parent) {
        val imgCover: ImageView = parent.imgCover
        val txtTitle: TextView = parent.txtTitle
        val txtLaunch: TextView = parent.txtLaunch
        val txtGender: TextView = parent.txtDirector
    }
}