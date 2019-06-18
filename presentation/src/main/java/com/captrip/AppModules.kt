package com.captrip

import com.captrip.data.repositories.TripRepositoryImpl
import com.captrip.domain.repositories.TripRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    fun getModule(): Module = module {
        factory<TripRepository> { TripRepositoryImpl() }
    }
}