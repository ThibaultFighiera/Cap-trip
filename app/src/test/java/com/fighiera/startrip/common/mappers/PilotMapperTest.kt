package com.fighiera.startrip.common.mappers

import com.fighiera.domain.entities.PilotDomain
import com.fighiera.startrip.common.entities.PilotUi
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