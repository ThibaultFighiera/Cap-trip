package com.captrip.data.mappers

import com.captrip.data.BuildConfig.SERVER_IMAGE_URL
import com.captrip.data.entities.*
import com.captrip.domain.entities.DistanceDomain
import com.captrip.domain.entities.LocationDomain
import com.captrip.domain.entities.PilotDomain
import com.captrip.domain.entities.TripItemDomain
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TripListItemMapper : (TripListItem) -> TripItemDomain {

    override fun invoke(tripListItem: TripListItem): TripItemDomain {
        val pickUp = PickUpMapper()(tripListItem.pick_up)
        val dropOff = DropOffMapper()(tripListItem.drop_off)
        val distance = DistanceMapper()(tripListItem.distance)
        val pilot = PilotMapper()(tripListItem.pilot)

        return TripItemDomain(tripListItem.id, pickUp, dropOff, tripListItem.duration, distance, pilot)
    }
}

private class DistanceMapper : (Distance) -> DistanceDomain {

    override fun invoke(distance: Distance) = DistanceDomain(distance.value, distance.unit)
}

private class PickUpMapper : (PickUp) -> LocationDomain {

    override fun invoke(pickUp: PickUp): LocationDomain {
        val date = DateMapper()(pickUp.date)

        return LocationDomain(LocationDomain.Type.PICK_UP, date, pickUp.name, SERVER_IMAGE_URL + pickUp.picture)
    }
}

private class DropOffMapper : (DropOff) -> LocationDomain {

    override fun invoke(dropOff: DropOff): LocationDomain {
        val date = DateMapper()(dropOff.date)
        return LocationDomain(LocationDomain.Type.DROP_OFF, date, dropOff.name, SERVER_IMAGE_URL + dropOff.picture)
    }
}

private class DateMapper : (String) -> Date? {
    override fun invoke(date: String): Date? {
        return try {
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}

private class PilotMapper : (Pilot) -> PilotDomain {

    override fun invoke(pilot: Pilot): PilotDomain {
        return PilotDomain(SERVER_IMAGE_URL + pilot.avatar, pilot.name, pilot.rating)
    }
}