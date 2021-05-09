package com.pahomovichk.remindMeDate.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.presentation.viewModel.EventsViewModel

class EventsFragment : Fragment() {

    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProvider(this).get(EventsViewModel::class.java)
        val root = inflater.inflate(R.layout.events_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.dates_text)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            textView.setVisibility(View.INVISIBLE)
        })
        return root
    }
}