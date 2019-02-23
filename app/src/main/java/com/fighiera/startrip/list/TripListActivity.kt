package com.fighiera.startrip.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fighiera.startrip.R
import com.fighiera.startrip.common.ui.ErrorViewHolder
import com.fighiera.startrip.detail.DetailActivity
import com.fighiera.startrip.list.ui.TripListViewHolder
import com.fighiera.startrip.list.viewmodel.TripListViewModel
import com.fighiera.startrip.list.viewmodel.TripListViewModelFactory
import com.fighiera.startrip.os.DomainLayerFactory
import kotlinx.android.synthetic.main.activity_main.*

class TripListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        val useCase = DomainLayerFactory.createTripUseCase()
        val viewModel = ViewModelProviders.of(this, TripListViewModelFactory(useCase))
            .get(TripListViewModel::class.java)

        TripListViewHolder(tripList, this, viewModel)
        ErrorViewHolder(window.decorView.rootView, tripList, this, viewModel.state) { viewModel.fetchList() }

        viewModel.displayDetail.observe(this, Observer { DetailActivity.start(this, it) })
    }
}
