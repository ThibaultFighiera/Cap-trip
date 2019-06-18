package com.captrip.list.mapper

import com.captrip.domain.entities.TripItemDomain
import com.captrip.common.mappers.PilotMapper
import com.captrip.list.entities.TripListItemUi

class TripListItemMapper : (TripItemDomain) -> TripListItemUi {
    override fun invoke(item: TripItemDomain): TripListItemUi {
        val pilot = PilotMapper()(item.pilot)
        return TripListItemUi(item.id, pilot, item.pickUp.name, item.dropOff.name)
    }
}