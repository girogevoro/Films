package com.girogevoro.films

import android.app.Application
import com.girogevoro.films.di.Di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    Di.viewModelModule(),
                    Di.filmApiModule(),
                    Di.repositoryModule(),
                    Di.useCasesModule()
                )
            )

        }
    }
}