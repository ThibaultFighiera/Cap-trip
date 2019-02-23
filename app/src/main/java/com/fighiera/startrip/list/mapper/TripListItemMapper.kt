package com.fighiera.startrip.list.mapper

import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.startrip.common.mappers.PilotMapper
import com.fighiera.startrip.list.entities.TripListItemUi

class TripListItemMapper : (TripItemDomain) -> TripListItemUi {
    override fun invoke(item: TripItemDomain): TripListItemUi {
        val pilot = PilotMapper()(item.pilot)
        return TripListItemUi(item.id, pilot, item.pickUp.name, item.dropOff.name)
    }
}