package com.fighiera.domain.usecases

import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.repositories.TripRepository
import io.reactivex.Single

class TripListUseCase(private val tripRepository: TripRepository) {

    fun getTripList(): Single<TripListDomain> = tripRepository.fetchTripList()
}