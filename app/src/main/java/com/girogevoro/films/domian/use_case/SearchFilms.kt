package com.girogevoro.films.domian.use_case

import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmsEntity
import com.girogevoro.films.domian.repository.FilmRepository

class SearchFilms(private val filmRepository: FilmRepository) {
    suspend fun get(query: String): AppState<FilmsEntity> = filmRepository.searchFilm(query)
}