package com.captrip.list.mapper

import com.captrip.domain.entities.TripListDomain
import com.captrip.list.entities.TripListItemUi

class TripListMapper : (TripListDomain) -> List<TripListItemUi> {

    override fun invoke(tripList: TripListDomain): List<TripListItemUi> {
        return tripList.list.map {
            TripListItemMapper()(it)
        }
    }
}
