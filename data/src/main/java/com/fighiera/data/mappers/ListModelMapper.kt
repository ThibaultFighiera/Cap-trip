package com.fighiera.data.mappers

import com.fighiera.data.entities.TripListItem
import com.fighiera.domain.entities.TripListDomain

class ListModelMapper : (List<TripListItem>) -> TripListDomain {

    override fun invoke(tripList: List<TripListItem>): TripListDomain {
        val transformedItemList = tripList.map {
            TripListItemMapper()(it)
        }
        return TripListDomain(transformedItemList)
    }
}