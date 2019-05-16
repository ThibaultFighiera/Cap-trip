package com.fighiera.data.repositories

import com.fighiera.data.entities.TripListItem
import com.fighiera.data.ext.okhttp.RemoteRequester
import com.fighiera.data.mappers.ListModelMapper
import com.fighiera.data.mappers.TripListItemMapper
import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.repositories.TripRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TripRepositoryImpl : TripRepository {

    override suspend fun fetchTripList(): TripListDomain {
        val response = RemoteRequester()
            .apply { addPathSegment(SCHEME_TRIP_LIST) }
            .requestJson()
        val postsType = object : TypeToken<List<TripListItem>>() {}.type
        val modelData = Gson().fromJson<List<TripListItem>>(response, postsType)
        return ListModelMapper()(modelData)
    }

    override suspend fun fetchTrip(id: Int): TripItemDomain {
        val response = RemoteRequester()
            .apply {
                addPathSegment(SCHEME_TRIPS)
                addPathSegment(id.toString())
            }
            .requestJson()

        val modelData = Gson().fromJson<TripListItem>(response, TripListItem::class.java)
        return TripListItemMapper()(modelData)
    }

    companion object {
        private const val SCHEME_TRIPS = "trip"
        private const val SCHEME_TRIP_LIST = "trips"
    }
}
