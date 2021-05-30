package com.pahomovichk.remindMeDate.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.domain.entity.Birthday

class YearlyEventAdapter internal constructor(
    private var birthdays: List<Birthday>
) : RecyclerView.Adapter<YearlyEventAdapter.ViewHolder>() {

    private var listener: YearlyEventClickListener? = null
    //val localFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.yearly_event_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val birthday = birthdays[position]
        viewHolder.birthdayName.text = birthday.name
        viewHolder.birthdayDate.text = "${birthday.date.format(Constants.viewLocalFormatter)}"
        viewHolder.container.setOnClickListener {
            listener?.onClick(birthday)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = birthdays.size

    fun setData(data: List<Birthday>) {
        this.birthdays = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: View = view.findViewById(R.id.birthday_card)
        val birthdayName: TextView = view.findViewById<TextView>(R.id.birth_name)
        val birthdayDate: TextView = view.findViewById<TextView>(R.id.birth_date)
    }

    fun setListener(yearlyEventClickListener: YearlyEventClickListener?) {
        listener = yearlyEventClickListener
    }
}