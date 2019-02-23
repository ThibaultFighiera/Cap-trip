package com.fighiera.startrip.os

import com.fighiera.domain.usecases.DetailUseCase
import com.fighiera.domain.usecases.TripListUseCase

interface DomainLayer{

    fun createTripUseCase() : TripListUseCase

    fun createDetailUseCase() : DetailUseCase
}