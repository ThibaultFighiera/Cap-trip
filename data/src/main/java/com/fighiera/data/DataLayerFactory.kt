package com.fighiera.data

import com.fighiera.data.repositories.TripRepositoryImpl
import com.fighiera.domain.repositories.TripRepository

class DataLayerFactory : DataLayer{

    override val tripRepository: TripRepository by lazy {
        TripRepositoryImpl()
    }
}