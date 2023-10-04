package com.girogevoro.films.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.girogevoro.films.BuildConfig
import com.girogevoro.films.data.remote_data_source.RemoteDataSource
import com.girogevoro.films.data.remote_data_source.RemoteDataSourceImpl
import com.girogevoro.films.data.retrofit.FilmApi
import com.girogevoro.films.data.retrofit.InterceptorApi
import com.girogevoro.films.ui.films.FilmsViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

object Di {
    val viewModelModule = module {
//        viewModel(){
//            FilmsViewModel()
//        }
    }

    val filmApiModule = module {
        single<Interceptor> {
            InterceptorApi()
        }

        single<Gson> {
            GsonBuilder()
                .create()
        }

        single<FilmApi> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .callTimeout(Duration.ofSeconds(15))
                        .connectTimeout(Duration.ofSeconds(15))
                        .readTimeout(Duration.ofSeconds(15))
                        .writeTimeout(Duration.ofSeconds(15))
                        .addInterceptor(interceptor = get())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                            if (BuildConfig.DEBUG) {
                                HttpLoggingInterceptor.Level.BODY
                            } else {
                                HttpLoggingInterceptor.Level.NONE
                            }
                        })
                        .build()
                )
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(FilmApi::class.java)
        }
    }


    val repositoryModule = module {
        single<RemoteDataSource> {
            RemoteDataSourceImpl(filmApi = get())
        }
    }
}