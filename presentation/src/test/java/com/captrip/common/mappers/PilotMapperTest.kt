package common.mappers

import com.captrip.domain.entities.PilotDomain
import com.captrip.common.entities.PilotUi
import com.captrip.common.mappers.PilotMapper
import org.junit.Assert
import org.junit.Test

class PilotMapperTest {

    @Test
    operator fun invoke() {
        val name = "MyName"
        val avatar = "http://test.fr/myPhoto"
        val rating = 3.0
        val givenItem = PilotDomain(avatar, name, rating)
        val whenItem = PilotMapper()(givenItem)
        Assert.assertEquals(whenItem, PilotUi(name, avatar, rating.toFloat()))
    }

    @Test
    fun invokeFailed() {
        val name = "MyName"
        val avatar = "http://test.fr/myPhoto"
        val rating = 3.0
        val givenItem = PilotDomain(avatar, name, rating)
        val whenItem = PilotMapper()(givenItem)
        Assert.assertEquals(whenItem, PilotUi(name, avatar, rating.toFloat()))
    }
}