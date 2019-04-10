package com.fighiera.domain.usecases

import android.util.Log
import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.repositories.TripRepository
import io.reactivex.Single

class TripListUseCase(private val tripRepository: TripRepository) {
    init {
        Log.d("TripListUseCase","tripRepository = "+System.identityHashCode(tripRepository).toString())
    }

    fun getTripList(): Single<TripListDomain> = tripRepository.fetchTripList()
}