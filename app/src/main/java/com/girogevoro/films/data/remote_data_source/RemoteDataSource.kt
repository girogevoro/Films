package com.girogevoro.films.data.remote_data_source

import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.domian.entity.FilmsEntity

interface RemoteDataSource {
    suspend fun getFilmsTop(adult: Boolean, page: Int): AppState<FilmsEntity>
    suspend fun getFilmDetailById(filmId: Int): AppState<FilmEntity>
}