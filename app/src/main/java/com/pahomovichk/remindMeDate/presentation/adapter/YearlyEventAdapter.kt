package com.pahomovichk.remindMeDate.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import java.time.LocalDate

class YearlyEventAdapter internal constructor(
    private var events: List<YearlyEvent>
) : RecyclerView.Adapter<YearlyEventAdapter.ViewHolder>() {

    private var listener: YearlyEventClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.yearly_event_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val event = events[position]
        Log.d("DATE", "${event.date}, ${LocalDate.now()}")
        viewHolder.eventName.text = event.name
        viewHolder.eventDate.text = "${event.date.format(Constants.VIEW_LOCAL_FORMATTER)}"
        viewHolder.container.setOnClickListener {
            listener?.onClick(event)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = events.size

    fun setData(data: List<YearlyEvent>) {
        this.events = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.yearly_event_card)
        val eventName: TextView = view.findViewById<TextView>(R.id.yearly_name)
        val eventDate: TextView = view.findViewById<TextView>(R.id.yearly_date)
    }

    fun setListener(yearlyEventClickListener: YearlyEventClickListener?) {
        listener = yearlyEventClickListener
    }
}
