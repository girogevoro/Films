package com.girogevoro.films

import android.app.Application
import com.girogevoro.films.di.Di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    Di.viewModelModule,
                    Di.filmApiModule,
                    Di.repositoryModule
                )
            )

        }
    }
}