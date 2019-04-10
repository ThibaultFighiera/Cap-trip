package com.fighiera.startrip

import android.app.Application
import com.fighiera.data.repositories.TripRepositoryImpl
import com.fighiera.domain.repositories.TripRepository
import com.fighiera.startrip.detail.DetailModules
import com.fighiera.startrip.list.TripListModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) {
                defaultLogger(Level.DEBUG)
            }

            modules(
                    AppModules.get(),
                    DetailModules.get(),
                    TripListModules.get()
            )
        }
    }
}