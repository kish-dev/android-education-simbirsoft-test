package com.education.test.simbirsoft.presentation.ui.calendar

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.education.test.simbirsoft.data.db.Event
import com.education.test_simbirsoft.R

class EventViewHolder(
    private val listener: Listener,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    interface Listener {
        fun onItemClick(eventId: Long)
    }

    private val timeText: AppCompatTextView = itemView.findViewById(R.id.item_event_view__time_tv)
    private val eventNameText: AppCompatTextView = itemView.findViewById(R.id.item_event_view__event_name_tv)
    private var currentEvent: Event? = null

    init {
        itemView.setOnClickListener {
            listener.onItemClick(currentEvent?.id ?: 0L)
        }
    }

    fun bind(event: Event) {
        currentEvent = event
        currentEvent?.apply {
            timeText.text = "$dateStart - $dateFinish"
            eventNameText.text = name
        }
    }
}