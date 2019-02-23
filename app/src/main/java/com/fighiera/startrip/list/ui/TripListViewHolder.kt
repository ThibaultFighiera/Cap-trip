package com.fighiera.startrip.list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fighiera.startrip.R
import com.fighiera.startrip.ext.ImageDownloader
import com.fighiera.startrip.list.entities.TripListItemUi
import com.fighiera.startrip.list.viewmodel.TripListViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.trip_item.*

class TripListViewHolder(override val containerView: View?, lifecycleOwner: LifecycleOwner, private val viewModel: TripListViewModel) : LayoutContainer {


    init {
        val tripAdapter = TripListAdapter { itemId -> viewModel.displayTrip(itemId) }
        tripList.apply {
            adapter = tripAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        viewModel.tripEntries.observe(lifecycleOwner, Observer { tripAdapter.submitList(it) })
    }
}

private class TripListAdapter(val listener: (Int) -> Unit) : ListAdapter<TripListItemUi, TripItemViewHolder>(ListItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripItemViewHolder {
        return TripItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.trip_item, parent, false), listener)
    }

    override fun onBindViewHolder(holder: TripItemViewHolder, position: Int) = holder.bind(getItem(position))

    class ListItemCallback : DiffUtil.ItemCallback<TripListItemUi>() {
        override fun areItemsTheSame(oldItem: TripListItemUi, newItem: TripListItemUi) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TripListItemUi, newItem: TripListItemUi) = oldItem.id == newItem.id
    }
}

private class TripItemViewHolder(override val containerView: View, val listener: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: TripListItemUi) {
        ImageDownloader().load(item.pilot.pilotUrl, tripItemAvatar)
        tripItemPilotName.text = item.pilot.name
        tripItemPickUp.text = item.pickUp
        tripItemDropOff.text = item.dropOff
        tripItemReview.apply {
            val ratingValue = item.pilot.rating
            rating = ratingValue
            visibility = if (ratingValue > 0f) View.VISIBLE else View.GONE
        }
        itemView.setOnClickListener { listener(item.id) }
    }
}
