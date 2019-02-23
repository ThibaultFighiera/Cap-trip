package com.fighiera.data.repositories

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.fighiera.data.entities.TripListItem
import com.fighiera.data.ext.okhttp.RemoteRequester
import com.fighiera.data.mappers.ListModelMapper
import com.fighiera.data.mappers.TripListItemMapper
import com.fighiera.domain.entities.TripItemDomain
import com.fighiera.domain.entities.TripListDomain
import com.fighiera.domain.repositories.TripRepository
import io.reactivex.Single

class TripRepositoryImpl : TripRepository {

    @WorkerThread
    override fun fetchTripList(): Single<TripListDomain> {
        return Single.fromCallable {
            val response = RemoteRequester()
                .apply { addPathSegment(SCHEME_TRIP_LIST) }
                .requestJson()
            val postsType = object : TypeToken<List<TripListItem>>() {}.type
            val modelData = Gson().fromJson<List<TripListItem>>(response, postsType)
            ListModelMapper()(modelData)
        }
    }

    @WorkerThread
    override fun fetchTrip(id: Int): Single<TripItemDomain> {
        return Single.fromCallable {
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
