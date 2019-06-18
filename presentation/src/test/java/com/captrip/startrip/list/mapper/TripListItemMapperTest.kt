package list.mapper

import com.captrip.domain.entities.DistanceDomain
import com.captrip.domain.entities.LocationDomain
import com.captrip.domain.entities.PilotDomain
import com.captrip.domain.entities.TripItemDomain
import com.captrip.common.entities.PilotUi
import com.captrip.list.entities.TripListItemUi
import com.captrip.list.mapper.TripListItemMapper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class TripListItemMapperTest {

    private val planet1 = "Planet1"
    private val planet2 = "Planet2"

    private val pilotAvatar = "http://test.fr/pilot"
    private val pilotName = "Obi Wan"
    private val pilotRating = 3.0

    private val pickUp = LocationDomain(LocationDomain.Type.PICK_UP, null, planet1, "")
    private val dropOff = LocationDomain(LocationDomain.Type.DROP_OFF, null, planet2, "")
    private val pilot = PilotDomain(pilotAvatar, pilotName, pilotRating)

    private val id = 1

    private lateinit var item : TripListItemUi

    @Before
    fun setUp(){
        val tripItemDomain = TripItemDomain(id, pickUp, dropOff, 0, mock(DistanceDomain::class.java), pilot)
        item = TripListItemMapper()(tripItemDomain)
    }

    @Test
    fun `is id valid`() {
        Assert.assertEquals(id, item.id)
    }

    @Test
    fun `is pickUp valid`() {
        Assert.assertEquals(planet1, item.pickUp)
    }

    @Test
    fun `is dropOff valid`() {
        Assert.assertEquals(planet2, item.dropOff)
    }

    @Test
    fun `is pilot valid`() {
        val pilotUi = PilotUi(pilotName, pilotAvatar, pilotRating.toFloat())
        Assert.assertEquals(pilotUi, item.pilot)
    }
}