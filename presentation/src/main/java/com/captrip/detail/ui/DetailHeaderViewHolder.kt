package com.captrip.detail.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.captrip.detail.model.DetailHeaderItem
import com.captrip.detail.viewmodel.DetailViewModel
import com.captrip.ext.ImageDownloader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.detail_toolbar_content.*

class DetailHeaderViewHolder(override val containerView: View?, lifecycleOwner: LifecycleOwner, viewModel: DetailViewModel) : LayoutContainer {
    init {
        viewModel.header.observe(lifecycleOwner, Observer { load(it) })
    }

    private fun load(item: DetailHeaderItem) {
        ImageDownloader().run {
            load(item.departureUrl, detailDeparturePicture)
            load(item.arrivalUrl, detailArrivalPicture)
        }
    }
}
