package com.captrip.list.entities

import com.captrip.common.entities.PilotUi

data class TripListItemUi(val id: Int, val pilot: PilotUi, val pickUp: String, val dropOff: String)
