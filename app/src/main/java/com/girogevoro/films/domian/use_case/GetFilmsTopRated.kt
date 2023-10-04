package com.girogevoro.films.domian.use_case

import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmsEntity
import com.girogevoro.films.domian.repository.FilmRepository

class GetFilmsTopRated(private val filmRepository: FilmRepository) {
    suspend fun get(adult: Boolean, page: Int): AppState<FilmsEntity> =
        filmRepository.getFilmsTop(adult, page)
}