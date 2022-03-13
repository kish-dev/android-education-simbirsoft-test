package com.education.test.simbirsoft.presentation.ui.calendar.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.education.test.simbirsoft.data.db.Event
import com.education.test.simbirsoft.presentation.ui.calendar.EventViewHolder
import com.education.test_simbirsoft.R

class EventListAdapter(private val listener: EventViewHolder.Listener):
    ListAdapter<Event, EventViewHolder>(EventDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            listener,
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class EventDiffUtil: DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        if (oldItem::class != newItem::class) return false
        return oldItem.id == newItem.id &&
                oldItem.dateStart == newItem.dateStart &&
                oldItem.dateFinish == newItem.dateFinish
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        if (oldItem::class != newItem::class) return false
        return oldItem == newItem
    }
}