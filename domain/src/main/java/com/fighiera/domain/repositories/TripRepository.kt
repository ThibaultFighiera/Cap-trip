package com.fighiera.domain.repositories

import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.entities.TripListDomain

interface TripRepository {

    suspend fun fetchTripList(): TripListDomain

    suspend fun fetchTrip(id: Int): TripItemDomain
}