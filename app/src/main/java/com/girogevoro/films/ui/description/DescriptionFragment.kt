package com.girogevoro.films.ui.description

import android.os.Bundle
import android.view.View

import coil.load
import com.girogevoro.films.BuildConfig
import com.girogevoro.films.databinding.FragmentDescriptionBinding
import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.utils.ui.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ID_FILM = "ID_FILM"


class DescriptionFragment : ViewBindingFragment<FragmentDescriptionBinding>() {
    private var idFilm: Int? = null
    private val descriptionViewModel: DescriptionViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idFilm = it.getInt(ID_FILM)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        descriptionViewModel.getFilmDetailLiveData().observe(viewLifecycleOwner){
            if (it is AppState.Success){
                showDescriptionFilm(it.data)
            }
        }
        idFilm?.let { descriptionViewModel.getFilmDetail(it) }
    }


    private fun showDescriptionFilm(descriptionFilm: FilmEntity) {
        binding.run {
            titleTextView.text = descriptionFilm.originalTitle
            overviewTextView.text = descriptionFilm.overview
            backdropImageView.load(BuildConfig.IMAGE_URL.plus(descriptionFilm.backdropPath))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(idFilm: Int) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_FILM, idFilm)
                }
            }
    }
}