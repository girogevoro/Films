package com.girogevoro.films.ui.films.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.girogevoro.films.BuildConfig
import com.girogevoro.films.databinding.FragmentFilmsItemBinding
import com.girogevoro.films.domian.entity.FilmsEntity
import kotlin.math.roundToInt

class FilmViewHolder(
    private val binding: FragmentFilmsItemBinding,
    private val itemClickListener: (Int) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        movie: FilmsEntity.Movie,
    ) {
        with(binding) {
            titleTextView.text = movie.title
            val popular = (movie.voteAverage * MULTIPLIER).roundToInt()
            filmRatingBar.setProgress(popular, true)
            posterImageView.load(BuildConfig.IMAGE_URL.plus(movie.posterPath))
        }
        itemView.setOnClickListener { itemClickListener(adapterPosition) }
    }

    companion object {
        private const val MULTIPLIER = 10
    }
}