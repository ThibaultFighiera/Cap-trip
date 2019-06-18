package com.captrip

import android.app.Application
import com.captrip.detail.DetailModules
import com.captrip.list.TripListModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            if (BuildConfig.DEBUG) {
                AndroidLogger(Level.DEBUG)
            }

            modules(listOf(AppModules.getModule(), DetailModules.getModule(), TripListModules.getModule()))
        }
    }
}