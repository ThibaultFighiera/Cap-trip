package com.captrip.data.repositories

import com.captrip.data.entities.TripListItem
import com.captrip.data.ext.okhttp.RemoteRequester
import com.captrip.data.mappers.ListModelMapper
import com.captrip.data.mappers.TripListItemMapper
import com.captrip.domain.entities.TripItemDomain
import com.captrip.domain.entities.TripListDomain
import com.captrip.domain.repositories.TripRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TripRepositoryImpl : TripRepository {

    override suspend fun fetchTripList(): TripListDomain {
        return withContext(Dispatchers.IO) {
            val response = RemoteRequester()
                .apply { addPathSegment(SCHEME_TRIP_LIST) }
                .requestJson()
            val postsType = object : TypeToken<List<TripListItem>>() {}.type
            val modelData = Gson().fromJson<List<TripListItem>>(response, postsType)
            ListModelMapper()(modelData)
        }
    }

    override suspend fun fetchTrip(id: Int): TripItemDomain {
        return withContext(Dispatchers.IO) {
            val response = RemoteRequester()
                .apply {
                    addPathSegment(SCHEME_TRIPS)
                    addPathSegment(id.toString())
                }
                .requestJson()

            val modelData = Gson().fromJson<TripListItem>(response, TripListItem::class.java)
            TripListItemMapper()(modelData)
        }
    }

    companion object {
        private const val SCHEME_TRIPS = "trip"
        private const val SCHEME_TRIP_LIST = "trips"
    }
}
