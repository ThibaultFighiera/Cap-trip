package com.captrip.data.mappers

import com.captrip.data.entities.TripListItem
import com.captrip.domain.entities.TripListDomain

class ListModelMapper : (List<TripListItem>) -> TripListDomain {

    override fun invoke(tripList: List<TripListItem>): TripListDomain {
        val transformedItemList = tripList.map {
            TripListItemMapper()(it)
        }
        return TripListDomain(transformedItemList)
    }
}