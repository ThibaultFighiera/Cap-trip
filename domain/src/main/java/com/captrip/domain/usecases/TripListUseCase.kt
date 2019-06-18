package com.captrip.domain.usecases

import android.util.Log
import com.captrip.domain.entities.TripListDomain
import com.captrip.domain.repositories.TripRepository

class TripListUseCase(private val tripRepository: TripRepository) {
    init {
        Log.d("TripListUseCase","tripRepository = "+System.identityHashCode(tripRepository).toString())
    }

    suspend fun getTripList(): TripListDomain = tripRepository.fetchTripList()

}