package com.fighiera.startrip

import com.fighiera.data.repositories.TripRepositoryImpl
import com.fighiera.domain.repositories.TripRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    fun get(): Module = module {
        factory<TripRepository> { TripRepositoryImpl() }
    }
}