package detail.mapper

import com.captrip.detail.mapper.DetailHeaderMapper
import com.captrip.domain.entities.DistanceDomain
import com.captrip.domain.entities.LocationDomain
import com.captrip.domain.entities.PilotDomain
import com.captrip.domain.entities.TripItemDomain
import com.captrip.detail.model.DetailHeaderItem
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock


class DetailHeaderMapperTest {

    private val picture1Url = "http://test.fr/planet1"
    private val picture2Url = "http://test.fr/planet2"

    // Given


    @Test
    operator fun invoke() {

        val pickUp = LocationDomain(LocationDomain.Type.PICK_UP, null, "", picture1Url)
        val dropOff = LocationDomain(LocationDomain.Type.DROP_OFF, null, "", picture2Url)

        val whenItem = DetailHeaderMapper()(TripItemDomain(-1, pickUp, dropOff, -1, mock(DistanceDomain::class.java), mock(PilotDomain::class.java)))
        Assert.assertEquals(DetailHeaderItem(picture1Url, picture2Url), whenItem)
    }

}