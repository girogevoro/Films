package com.girogevoro.films.ui.films.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.films.databinding.FragmentFilmsItemBinding
import com.girogevoro.films.domian.entity.FilmsEntity

class FilmAdapter(
    private val itemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<FilmViewHolder>() {
    private var filmsList: List<FilmsEntity.Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            FragmentFilmsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener
        )
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }

    fun setFilmsList(filmsList: List<FilmsEntity.Movie>) {
        val oldSize = this.filmsList.size
        this.filmsList = filmsList
        notifyItemRangeChanged(oldSize, this.filmsList.size)
    }
}