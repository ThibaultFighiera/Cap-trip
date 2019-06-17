package com.fighiera.startrip.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fighiera.startrip.R
import com.fighiera.startrip.common.ui.ErrorViewHolder
import com.fighiera.startrip.detail.DetailActivity
import com.fighiera.startrip.list.ui.TripListViewHolder
import com.fighiera.startrip.list.viewmodel.TripListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.currentScope

class TripListActivity : AppCompatActivity() {

    private val viewModel: TripListViewModel by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        TripListViewHolder(tripList, this, viewModel)
        ErrorViewHolder(window.decorView.rootView, tripList, this, viewModel.state) { viewModel.fetchList() }
        viewModel.run {
            displayTrip.observe(this@TripListActivity, Observer { DetailActivity.start(this@TripListActivity, it) })
            fetchList()
        }
    }
}
