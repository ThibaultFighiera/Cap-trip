package com.captrip.domain.repositories

import com.captrip.domain.entities.TripItemDomain
import com.captrip.domain.entities.TripListDomain

interface TripRepository {

    suspend fun fetchTripList(): TripListDomain

    suspend fun fetchTrip(id: Int): TripItemDomain
}