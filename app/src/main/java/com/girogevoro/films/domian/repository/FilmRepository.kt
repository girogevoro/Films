package com.girogevoro.films.domian.repository

import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.domian.entity.FilmsEntity

interface FilmRepository {
    suspend fun searchFilm(query: String):AppState<FilmsEntity>
    suspend fun getFilmsTop(adult: Boolean, page: Int): AppState<FilmsEntity>
    suspend fun getFilmDetailById(filmId: Int): AppState<FilmEntity>
}