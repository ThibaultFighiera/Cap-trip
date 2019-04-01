package com.fighiera.domain.usecases

import android.util.Log
import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.repositories.TripRepository
import io.reactivex.Single

class DetailUseCase(private val tripRepository: TripRepository) {
    init {
        Log.d("DetailUseCase","tripRepository = "+System.identityHashCode(tripRepository).toString())
    }

    fun getTrip(id : Int): Single<TripItemDomain> = tripRepository.fetchTrip(id)
}