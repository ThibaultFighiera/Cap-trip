package com.fighiera.startrip.os

import com.fighiera.data.DataLayer
import com.fighiera.data.DataLayerFactory
import com.fighiera.domain.usecases.DetailUseCase
import com.fighiera.domain.usecases.TripListUseCase

object DomainLayerFactory : DomainLayer {

    private val dataLayer: DataLayer = DataLayerFactory()

    override fun createTripUseCase() = TripListUseCase(dataLayer.tripRepository)

    override fun createDetailUseCase() = DetailUseCase(dataLayer.tripRepository)
}