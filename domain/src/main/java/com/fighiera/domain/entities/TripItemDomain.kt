package com.fighiera.domain.entities

data class TripItemDomain(val id: Int, val pickUp: LocationDomain, val dropOff: LocationDomain, val duration: Long, val distance: DistanceDomain, val pilot: PilotDomain)