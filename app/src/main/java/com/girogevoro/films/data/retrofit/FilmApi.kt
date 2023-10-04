package com.girogevoro.films.data.retrofit

import com.girogevoro.films.BuildConfig
import com.girogevoro.films.domian.entity.FilmEntity
import com.girogevoro.films.domian.entity.FilmsEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {

    @GET("search/movie?api_key=${BuildConfig.KEY}&language=ru-RU")
    fun searchFilmsAsync(@Query("query") query: String): Deferred<FilmsEntity>

    @GET("movie/top_rated?api_key=${BuildConfig.KEY}&language=ru-RU")
    fun getFilmsTopAsync(
        @Query("include_adult") adult: Boolean,
        @Query("page") page: Int,
    ): Deferred<FilmsEntity>

    @GET("movie/{movie_id}?api_key=${BuildConfig.KEY}&language=ru-RU")
    fun getFilmDetailByIdAsync(@Path("movie_id") movieId: Int): Deferred<FilmEntity>
}
