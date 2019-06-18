package com.captrip.detail.mapper

import com.captrip.domain.entities.TripItemDomain
import com.captrip.detail.model.DetailHeaderItem

class DetailHeaderMapper : (TripItemDomain) -> DetailHeaderItem {
    override fun invoke(item: TripItemDomain): DetailHeaderItem {
        return DetailHeaderItem(item.pickUp.pictureUrl, item.dropOff.pictureUrl)
    }
}
