package com.girogevoro.films.data

import com.girogevoro.films.data.remote_data_source.RemoteDataSource
import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.domian.entity.FilmsEntity
import com.girogevoro.films.domian.repository.FilmRepository

class FilmRepositoryImpl(private val dataSource: RemoteDataSource) : FilmRepository {
    override suspend fun searchFilm(query: String): AppState<FilmsEntity> {
        return dataSource.searchFilm(query)
    }

    override suspend fun getFilmsTop(adult: Boolean, page: Int): AppState<FilmsEntity> {
        return dataSource.getFilmsTop(adult, page)
    }

    override suspend fun getFilmDetailById(filmId: Int): AppState<FilmEntity> {
        return dataSource.getFilmDetailById(filmId)
    }

}