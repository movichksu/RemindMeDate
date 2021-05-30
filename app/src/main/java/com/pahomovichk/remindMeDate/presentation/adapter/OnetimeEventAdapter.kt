package com.pahomovichk.remindMeDate.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.domain.entity.Event

class OnetimeEventAdapter internal constructor(
        private var events: List<Event>
) : RecyclerView.Adapter<OnetimeEventAdapter.ViewHolder>() {

    private var listenerOnetime: OnetimeEventClickListener? = null
    //val localFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.onetime_event_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val event = events[position]
        viewHolder.eventName.text = event.name
        viewHolder.eventDate.text = "${event.date.format(Constants.viewLocalFormatter)}"
        viewHolder.container.setOnClickListener {
            listenerOnetime?.onClick(event)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = events.size

    fun setData(data: List<Event>) {
        this.events = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.event_card)
        val eventName: TextView = view.findViewById<TextView>(R.id.event_name)
        val eventDate: TextView = view.findViewById<TextView>(R.id.event_date)
    }

    fun setListener(onetimeEventClickListener: OnetimeEventClickListener?) {
        listenerOnetime = onetimeEventClickListener
    }
}