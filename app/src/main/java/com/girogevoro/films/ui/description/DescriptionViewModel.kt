package com.girogevoro.films.ui.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.domian.use_case.GetFilmDetailById
import kotlinx.coroutines.launch

class DescriptionViewModel(private val getFilmDetailById: GetFilmDetailById) : ViewModel() {
    private val filmDetailLiveData: MutableLiveData<AppState<FilmEntity>> = MutableLiveData()

    fun getFilmDetailLiveData(): LiveData<AppState<FilmEntity>> {
        return filmDetailLiveData
    }

    fun getFilmDetail(idFilm: Int) {
        viewModelScope.launch {
            val film = getFilmDetailById.get(idFilm)
            filmDetailLiveData.postValue(film)
        }
    }

}