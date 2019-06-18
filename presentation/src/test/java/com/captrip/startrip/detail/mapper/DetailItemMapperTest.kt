package detail.mapper

import com.captrip.domain.entities.DistanceDomain
import com.captrip.domain.entities.LocationDomain
import com.captrip.domain.entities.PilotDomain
import com.captrip.domain.entities.TripItemDomain
import com.captrip.R
import com.captrip.common.entities.PilotUi
import com.captrip.detail.mapper.DetailItemMapper
import com.captrip.detail.model.DetailItem
import com.captrip.detail.model.LocationDetail
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailItemMapperTest {

    private val departureDate = Date().apply { time = 1551128237790 }
    private val planet1 = "Planet1"
    private val picture1Url = "http://test.fr/planet1"

    private val arrivalDate = Date().apply { time = 1551129237790 }
    private val planet2 = "Planet2"
    private val picture2Url = "http://test.fr/planet2"

    private val pilotAvatar = "http://test.fr/pilot"
    private val pilotName = "Obi Wan"
    private val pilotRating = 3.0

    private val distanceValue: Long = 10000
    private val distanceUnit = "km"

    private val duration: Long = 80000
    private val pickUp = LocationDomain(LocationDomain.Type.PICK_UP, departureDate, planet1, picture1Url)
    private val dropOff = LocationDomain(LocationDomain.Type.DROP_OFF, arrivalDate, planet2, picture2Url)
    private val distance = DistanceDomain(distanceValue, distanceUnit)
    private val pilot = PilotDomain(pilotAvatar, pilotName, pilotRating)

    private lateinit var detailItem: DetailItem

    @Before
    fun setUp() {
        detailItem = DetailItemMapper()(TripItemDomain(0, pickUp, dropOff, duration, distance, pilot))
    }

    @Test
    fun `is pickup valid`() {
        val expectedDate = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault())
            .format(departureDate)
        val departure = LocationDetail(R.string.departure, expectedDate, planet1, picture1Url)
        Assert.assertEquals(detailItem.pickUp, departure)
    }

    @Test
    fun `is dropOff valid`() {
        val expectedDate = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault())
            .format(arrivalDate)
        val arrival = LocationDetail(R.string.arrival, expectedDate, planet2, picture2Url)
        Assert.assertEquals(detailItem.dropOff, arrival)
    }

    @Test
    fun `is pilot valid`() {
        val pilot = PilotUi("Obi Wan", "http://test.fr/pilot", 3f)
        Assert.assertEquals(pilot, detailItem.pilot)
    }

    @Test
    fun `is distance valid`() {
        val expected = NumberFormat.getInstance().format(distance.value)
        Assert.assertEquals("$expected ${distance.unit}", detailItem.distance)
    }

    @Test
    fun `is duration valid`() {
        val date = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            .run { format(Date(duration)) }
        Assert.assertEquals(date, detailItem.duration)
    }
}