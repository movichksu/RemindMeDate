package com.pahomovichk.remindMeDate.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.entity.Event

class EventAdapter internal constructor(
        private var events: List<Event>
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private var listener: EventClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.birthday_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val event = events[position]
        viewHolder.birthdayName.text = event.eventName
        viewHolder.birthdayDate.text = "${event.eventDate.dayOfMonth} ${event.eventDate.month}-${event.eventDate.year}"
        viewHolder.container.setOnClickListener {
            listener?.onClick(event)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = events.size

    fun setData(data: List<Event>) {
        this.events = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.birthday_card)
        val birthdayName: TextView = view.findViewById<TextView>(R.id.birth_name)
        val birthdayDate: TextView = view.findViewById<TextView>(R.id.birth_date)
    }

    fun setListener(eventClickListener: EventClickListener?) {
        listener = eventClickListener
    }
}
