package com.girogevoro.films.domian.use_case

import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.domian.repository.FilmRepository

class GetFilmDetailById(private val filmRepository: FilmRepository) {
    suspend fun get(filmId: Int): AppState<FilmEntity> = filmRepository.getFilmDetailById(filmId)
}