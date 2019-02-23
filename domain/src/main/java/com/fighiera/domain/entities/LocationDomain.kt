package com.fighiera.domain.entities

import java.util.*

data class LocationDomain(val type: Type, val date: Date?, val name: String, val pictureUrl: String){
    enum class Type{
        PICK_UP,
        DROP_OFF
    }
}