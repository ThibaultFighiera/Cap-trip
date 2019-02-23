package com.fighiera.startrip.common.mappers

import com.fighiera.domain.entities.PilotDomain
import com.fighiera.startrip.common.entities.PilotUi

class PilotMapper : (PilotDomain) -> PilotUi {
    override fun invoke(pilot: PilotDomain): PilotUi {
        return PilotUi(pilot.name, pilot.avatar, pilot.rating.toFloat())
    }
}