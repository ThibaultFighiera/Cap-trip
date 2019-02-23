package com.fighiera.domain.repositories

import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.entities.TripListDomain
import io.reactivex.Single

interface TripRepository {

    fun fetchTripList() : Single<TripListDomain>

    fun fetchTrip(id: Int): Single<TripItemDomain>
}