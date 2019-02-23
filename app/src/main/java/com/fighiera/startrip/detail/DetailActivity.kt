package com.fighiera.startrip.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.fighiera.startrip.R
import com.fighiera.startrip.common.ui.ErrorViewHolder
import com.fighiera.startrip.detail.ui.DetailHeaderViewHolder
import com.fighiera.startrip.detail.ui.DetailViewHolder
import com.fighiera.startrip.detail.viewmodel.DetailViewModel
import com.fighiera.startrip.detail.viewmodel.DetailViewModelFactory
import com.fighiera.startrip.os.DomainLayerFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_toolbar.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initialize(intent.getIntExtra(EXTRA_ID, 0))
    }

    private fun initialize(id: Int) {
        val viewModel = ViewModelProviders.of(
            this,
            DetailViewModelFactory(id, DomainLayerFactory.createDetailUseCase())
        ).get(DetailViewModel::class.java)
        DetailHeaderViewHolder(detailToolbar, this, viewModel)
        DetailViewHolder(detailContent, this, viewModel)
        ErrorViewHolder(window.decorView.rootView, detailContent, this, viewModel.state) { viewModel.fetchList() }
    }

    companion object {
        private const val EXTRA_ID = "extra_id"

        fun start(context: Context, id: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            context.startActivity(intent)
        }
    }
}
