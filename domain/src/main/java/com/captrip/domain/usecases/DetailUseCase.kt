package com.captrip.domain.usecases

import android.util.Log
import com.captrip.domain.entities.TripItemDomain
import com.captrip.domain.repositories.TripRepository

class DetailUseCase(private val tripRepository: TripRepository) {
    init {
        Log.d("DetailUseCase","tripRepository = "+System.identityHashCode(tripRepository).toString())
    }

    suspend fun getTrip(id : Int): TripItemDomain = tripRepository.fetchTrip(id)
}