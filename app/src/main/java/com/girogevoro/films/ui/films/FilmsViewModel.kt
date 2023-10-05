package com.girogevoro.films.ui.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmsEntity
import com.girogevoro.films.domian.use_case.GetFilmsTop
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val getFilmsTopUseCase: GetFilmsTop,
) : ViewModel() {

    private val filmsLiveData: MutableLiveData<AppState<FilmsEntity>> =
        MutableLiveData<AppState<FilmsEntity>>()
    private var nextPage = START_PAGE

    private var lastPage: Int = 1
    private val allMovies: ArrayList<FilmsEntity.Movie> = arrayListOf()

    private val isLastPageLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getFilmsLiveData(): LiveData<AppState<FilmsEntity>> = filmsLiveData
    fun getIsLastPageLiveData(): LiveData<Boolean> = isLastPageLiveData

    fun getFilms() =
        viewModelScope.launch {
            filmsLiveData.postValue(AppState.Loading)
            val films = getFilmsTopUseCase.get(false, nextPage)
            if (films is AppState.Success) {
                val filmsList = films.data
                allMovies.addAll(filmsList.movies)
                filmsLiveData.postValue(
                    AppState.Success<FilmsEntity>(filmsList.copy(movies = allMovies))
                )
                nextPage = films.data.page + 1
                if (lastPage != films.data.totalPages) {
                    lastPage++
                    isLastPageLiveData.value = false
                } else
                    isLastPageLiveData.value = true
            }else if(films is AppState.Error){
                filmsLiveData.postValue(films)
            }
        }

    fun onClickFilm(position: Int) {

    }


    companion object {
        private const val DEFAULT_ERROR = "Default error"
        private const val NOT_CONNECT = "No internet connection"
        private const val START_PAGE = 1
    }
}