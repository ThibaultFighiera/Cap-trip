package com.captrip.detail.ui

import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.captrip.R
import com.captrip.detail.model.DetailItem
import com.captrip.detail.model.LocationDetail
import com.captrip.detail.viewmodel.DetailViewModel
import com.captrip.ext.ImageDownloader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.detail_location.*
import kotlinx.android.synthetic.main.detail_pilot.*
import kotlinx.android.synthetic.main.detail_rating.*
import kotlinx.android.synthetic.main.detail_trip_info.*
import kotlinx.android.synthetic.main.detailed_content.*

class DetailViewHolder(override val containerView: View?, lifecycleOwner: LifecycleOwner, viewModel: DetailViewModel) :
    LayoutContainer {

    init {
        viewModel.trip.observe(lifecycleOwner, Observer { load(it) })
    }

    private fun load(item: DetailItem) {
        setPilot(item)
        setTripLocations(item)
        setInfo(item)
        setRating(item)
    }

    private fun setPilot(item: DetailItem) {
        item.pilot.run {
            ImageDownloader().load(pilotUrl, detailPilotAvatar)
            detailPilotName.text = name
        }
    }

    private fun setTripLocations(item: DetailItem) {
        LocationBinder(detailDeparture, item.pickUp)
        LocationBinder(detailArrival, item.dropOff)
    }

    private fun setInfo(item: DetailItem) {
        InfoBinder(detailDistance, R.string.distance, item.distance)
        InfoBinder(detailDuration, R.string.duration, item.duration)
    }

    private fun setRating(item: DetailItem) {
        val ratingValue = item.pilot.rating
        detailRatingValue.rating = ratingValue
        if (ratingValue > 0) {
            detailNoRating.visibility = View.GONE
            detailRatingValue.visibility = View.VISIBLE
        } else {
            detailNoRating.visibility = View.VISIBLE
            detailRatingValue.visibility = View.GONE
        }
    }

    inner class LocationBinder(override val containerView: View?, location: LocationDetail) : LayoutContainer {
        init {
            detailLocationType.setText(location.typeRes)
            detailLocationName.text = location.name
            detailLocationTime.text = location.date
        }
    }

    inner class InfoBinder(override val containerView: View?, @StringRes title: Int, value: String) : LayoutContainer {
        init {
            detailInfoTitle.setText(title)
            detailInfoValue.text = value
        }
    }
}