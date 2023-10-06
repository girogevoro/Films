package com.girogevoro.films.data.remote_data_source

import com.girogevoro.films.data.retrofit.FilmApi
import com.girogevoro.films.domian.AppState
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.domian.entity.FilmsEntity

class RemoteDataSourceImpl(private val filmApi: FilmApi) : RemoteDataSource {
    override suspend fun getFilmsTop(adult: Boolean, page: Int): AppState<FilmsEntity> =
        try {
            val result = filmApi.getFilmsTopAsync(adult, page).await()

            if (result.movies.isNotEmpty()) {
                AppState.Success(result)
            } else {
                AppState.Error(Exception(NO_DATA))
            }
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getFilmDetailById(filmId: Int): AppState<FilmEntity> =
        try {
            val result = filmApi.getFilmDetailByIdAsync(filmId).await()
            AppState.Success(result)
        } catch (err: Exception) {
            AppState.Error(err)
        }


    companion object {
        const val NO_DATA = "error NO_DATA"
    }
}