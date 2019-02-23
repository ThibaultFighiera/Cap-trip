package com.fighiera.startrip.list.mapper

import com.fighiera.domain.entities.TripListDomain
import com.fighiera.startrip.list.entities.TripListItemUi

class TripListMapper : (TripListDomain) -> List<TripListItemUi> {

    override fun invoke(tripList: TripListDomain): List<TripListItemUi> {
        return tripList.list.map {
            TripListItemMapper()(it)
        }
    }
}
