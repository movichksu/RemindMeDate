package com.pahomovichk.remindMeDate.presentation.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.R

class CalendarFragment : Fragment() {

    private lateinit var calendarViewModel: CalendarViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        calendarViewModel =
                ViewModelProvider(this).get(CalendarViewModel::class.java)
        val root = inflater.inflate(R.layout.calendar_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.calendar_text)
        calendarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}