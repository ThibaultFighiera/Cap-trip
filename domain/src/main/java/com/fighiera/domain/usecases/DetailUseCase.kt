package com.fighiera.domain.usecases

import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.repositories.TripRepository
import io.reactivex.Single

class DetailUseCase(private val tripRepository: TripRepository) {

    fun getTrip(id : Int): Single<TripItemDomain> = tripRepository.fetchTrip(id)
}