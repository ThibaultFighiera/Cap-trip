package com.captrip.detail.mapper

import com.captrip.domain.entities.DistanceDomain
import com.captrip.domain.entities.LocationDomain
import com.captrip.domain.entities.TripItemDomain
import com.captrip.R
import com.captrip.common.mappers.PilotMapper
import com.captrip.detail.model.DetailItem
import com.captrip.detail.model.LocationDetail
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class DetailItemMapper : (TripItemDomain) -> DetailItem {
    override fun invoke(item: TripItemDomain): DetailItem {
        val pickUp = LocationMapper()(item.pickUp)
        val dropOff = LocationMapper()(item.dropOff)
        val distance = DistanceMapper()(item.distance)
        val duration = DurationMapper()(item.duration)
        return DetailItem(
            pickUp,
            dropOff,
            duration,
            distance,
            PilotMapper()(item.pilot)
        )
    }
}

private class LocationMapper : (LocationDomain) -> LocationDetail {

    override fun invoke(location: LocationDomain): LocationDetail {
        val date = location.date?.let {
            DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault())
                .format(location.date)
        }
        val typeRes = if (location.type == LocationDomain.Type.PICK_UP) R.string.departure else R.string.arrival

        return LocationDetail(typeRes, date, location.name, location.pictureUrl)
    }
}

private class DistanceMapper : (DistanceDomain) -> String {
    override fun invoke(distance: DistanceDomain): String {
        val formattedDistance = NumberFormat.getInstance().format(distance.value)
        return "$formattedDistance ${distance.unit}"
    }
}

private class DurationMapper : (Long) -> String {
    override fun invoke(duration: Long): String {
        return SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
            .run { format(Date(duration)) }
    }

    companion object {
        private const val TIME_FORMAT = "HH:mm:ss"
    }
}
