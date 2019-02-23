package com.fighiera.startrip.detail.mapper

import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.startrip.detail.model.DetailHeaderItem

class DetailHeaderMapper : (TripItemDomain) -> DetailHeaderItem {
    override fun invoke(item: TripItemDomain): DetailHeaderItem {
        return DetailHeaderItem(item.pickUp.pictureUrl, item.dropOff.pictureUrl)
    }
}
