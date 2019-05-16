package com.fighiera.domain.usecases

import android.util.Log
import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.repositories.TripRepository

class DetailUseCase(private val tripRepository: TripRepository) {
    init {
        Log.d("DetailUseCase","tripRepository = "+System.identityHashCode(tripRepository).toString())
    }

    suspend fun getTrip(id : Int): TripItemDomain = tripRepository.fetchTrip(id)
}