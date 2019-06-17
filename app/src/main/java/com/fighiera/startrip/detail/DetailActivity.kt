package com.fighiera.startrip.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fighiera.startrip.R
import com.fighiera.startrip.common.ui.ErrorViewHolder
import com.fighiera.startrip.detail.ui.DetailHeaderViewHolder
import com.fighiera.startrip.detail.ui.DetailViewHolder
import com.fighiera.startrip.detail.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_toolbar.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

    private val id: Int by lazy { intent.getIntExtra(EXTRA_ID, 0) }
    private val viewModel by viewModel<DetailViewModel>(parameters = { parametersOf(id) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initialize()
    }

    private fun initialize() {
        DetailHeaderViewHolder(detailToolbar, this, viewModel)
        DetailViewHolder(detailContent, this, viewModel)
        ErrorViewHolder(window.decorView.rootView, detailContent, this, viewModel.state) { viewModel.fetchTrip() }
        viewModel.fetchTrip()
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
