package com.captrip.common.mappers

import com.captrip.domain.entities.PilotDomain
import com.captrip.common.entities.PilotUi

class PilotMapper : (PilotDomain) -> PilotUi {
    override fun invoke(pilot: PilotDomain): PilotUi {
        return PilotUi(pilot.name, pilot.avatar, pilot.rating.toFloat())
    }
}