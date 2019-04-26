package com.fighiera.domain.usecases

import android.util.Log
import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.repositories.TripRepository

class TripListUseCase(private val tripRepository: TripRepository) {
    init {
        Log.d("TripListUseCase","tripRepository = "+System.identityHashCode(tripRepository).toString())
    }

    suspend fun getTripList(): TripListDomain = tripRepository.fetchTripList()

}