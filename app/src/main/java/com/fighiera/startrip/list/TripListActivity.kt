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
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.koin.getViewModel

class TripListActivity : AppCompatActivity() {

    private val viewModel by viewModel<TripListViewModel>(scope = currentScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        TripListViewHolder(tripList, this, viewModel)
        ErrorViewHolder(window.decorView.rootView, tripList, this, viewModel.state) { viewModel.fetchList() }
        viewModel.displayDetail.observe(this, Observer { DetailActivity.start(this, it) })
    }
}
