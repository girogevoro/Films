package com.girogevoro.films.ui.films

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.films.databinding.FragmentFilmsBinding
import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmsEntity
import com.girogevoro.films.ui.films.adapter.FilmAdapter
import com.girogevoro.films.utils.ui.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class FilmsFragment : ViewBindingFragment<FragmentFilmsBinding>() {

    private val filmsAdapter: FilmAdapter = FilmAdapter { filmsViewModel.onClickFilm(it) }
    private var isLastPage: Boolean = false

    private var isLoading: Boolean = false
    private val filmsViewModel: FilmsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFilmsRecyclerView()
        filmsViewModel.apply {
            getIsLastPageLiveData().observe(viewLifecycleOwner) {
                isLastPage = it
            }
            getFilmsLiveData().observe(viewLifecycleOwner) {
                isLoading = false
                renderData(it)
            }
            getFilms()
        }


    }

    private fun renderData(appState: AppState<FilmsEntity>) {
        when (appState) {
            AppState.Loading -> {
                isLoading = true
            }

            is AppState.Error -> {
                showError("Error")
            }

            is AppState.Success -> {
                filmsAdapter.setFilmsList(appState.data.movies)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun initFilmsRecyclerView() {
        binding.filmsRecyclerView.apply {
            val filmsLayoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            layoutManager = filmsLayoutManager
            adapter = filmsAdapter
            addOnScrollListener(object : PaginationScrollListener(filmsLayoutManager) {
                override fun isLastPage(): Boolean = isLastPage

                override fun isLoading(): Boolean = isLoading

                override fun loadMoreItems() {
                    filmsViewModel.getFilms()
                }
            })
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = FilmsFragment()
    }
}