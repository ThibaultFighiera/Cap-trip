package com.fighiera.data

import com.fighiera.domain.repositories.TripRepository

interface DataLayer {

    val tripRepository: TripRepository
}