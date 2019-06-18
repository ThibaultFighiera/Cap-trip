package com.captrip.common.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.captrip.common.entities.Status
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.error_content.*

class ErrorViewHolder(override val containerView: View?, private val mainContent: View?, lifecycleOwner: LifecycleOwner, state: LiveData<Status>, reload: () -> Unit) :
    LayoutContainer {
    init {
        statusRetry.setOnClickListener { reload() }
        state.observe(lifecycleOwner, Observer { onStateChanged(it) })
    }

    private fun onStateChanged(state: Status) {
        when (state) {
            Status.IDLE -> {
                mainContent?.visibility = View.VISIBLE
                statusRetry.visibility = View.GONE
                statusLoader.visibility = View.GONE
            }
            Status.LOADING -> {
                mainContent?.visibility = View.INVISIBLE
                statusRetry.visibility = View.GONE
                statusLoader.visibility = View.VISIBLE
            }
            Status.ERROR -> {
                mainContent?.visibility = View.GONE
                statusRetry.visibility = View.VISIBLE
                statusLoader.visibility = View.GONE
            }
        }
    }
}