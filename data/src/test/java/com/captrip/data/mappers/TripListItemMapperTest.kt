package com.captrip.data.mappers

import com.captrip.data.BuildConfig
import com.captrip.data.entities.*
import com.captrip.domain.entities.DistanceDomain
import com.captrip.domain.entities.LocationDomain
import com.captrip.domain.entities.PilotDomain
import com.captrip.domain.entities.TripItemDomain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TripListItemMapperTest {


    private val id = 1

    private val pilotAvatar = "/pilot"
    private val pilotName = "Pilot"
    private val pilotRating = 3.0
    private val pilot = Pilot(pilotAvatar, pilotName, pilotRating)

    private val departureName = "Planet1"
    private val departurePicture = "/planet1"
    private val departureDate = "2017-12-09T14:12:51Z"
    private val pickUp = PickUp(departureDate, departureName, departurePicture)

    private val arrivalName = "Planet2"
    private val arrivalPicture = "/planet2"
    private val arrivalDate = "2017-12-09T19:35:51Z"
    private val dropOff = DropOff(arrivalDate, arrivalName, arrivalPicture)

    private val distanceUnit = "km"
    private val distanceValue: Long = 10000
    private val distance = Distance(distanceUnit, distanceValue)

    private val duration: Long = 10000

    private val tripListItem = TripListItem(distance, dropOff, duration, id, pickUp, pilot)
    private lateinit var tripListItemDomain: TripItemDomain

    @Before
    fun setUp() {
        tripListItemDomain = TripListItemMapper()(tripListItem)
    }

    //    val id: Int, val pickUp: LocationDomain, val dropOff: LocationDomain, val duration: Long, val distance: DistanceDomain, val pilot: PilotDomain)
    @Test
    fun isDistanceValid() {
        val expected = DistanceDomain(distanceValue, distanceUnit)
        assertEquals(expected, tripListItemDomain.distance)
    }

    @Test
    fun isDropOffValid() {
        val expected = LocationDomain(LocationDomain.Type.DROP_OFF, stringToDate(arrivalDate), arrivalName, BASE_URL + arrivalPicture)
        assertEquals(expected, tripListItemDomain.dropOff)
    }

    private fun stringToDate(date : String): Date? {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(date)
    }

    @Test
    fun isDurationValid() {
        assertEquals(duration, tripListItemDomain.duration)
    }

    @Test
    fun isPickUpValid() {
        val expected = LocationDomain(LocationDomain.Type.PICK_UP, stringToDate(departureDate), departureName, BASE_URL + departurePicture)
        assertEquals(expected, tripListItemDomain.pickUp)
    }

    @Test
    fun isIdValid() {
        assertEquals(id, 1)
    }

    @Test
    fun isPilotValid() {
        val expected = PilotDomain(BASE_URL + pilotAvatar, pilotName, pilotRating)
        assertEquals(expected, tripListItemDomain.pilot)
    }

    companion object {
        private const val BASE_URL = BuildConfig.SERVER_IMAGE_URL
    }
}