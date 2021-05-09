package com.pahomovichk.remindMeDate.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.EventItemActivity
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.entity.Event
import com.pahomovichk.remindMeDate.presentation.adapter.EventAdapter
import com.pahomovichk.remindMeDate.presentation.adapter.EventClickListener
import com.pahomovichk.remindMeDate.presentation.viewModel.EventsViewModel

class EventsFragment : Fragment(), EventClickListener {

    companion object {
        fun newInstance() =
                EventsFragment()
    }

    private lateinit var viewModel: EventsViewModel

    private lateinit var eventsList: RecyclerView
    private var adapter = EventAdapter(listOf())

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsList = view.findViewById(R.id.events_list)
        eventsList.layoutManager = LinearLayoutManager(requireContext())
        eventsList.adapter = adapter
        adapter.setListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EventsViewModel::class.java)

        viewModel.getEvents().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    override fun onClick(event: Event) {
        //viewModel.onItemSelected(birthday)

        val intent = Intent(this.context, EventItemActivity::class.java)
        intent.putExtra(Constants.EVENT_ID, event.id)
        intent.putExtra(Constants.EVENT_NAME, event.name)
        intent.putExtra(Constants.EVENT_DATE, "${event.date.month.toString().toLowerCase()} ${event.date.dayOfMonth}, ${event.date.year}");
        intent.putExtra(Constants.EVENT_COMMENT, event.comments);
        startActivity(intent)
    }

    override fun onDestroyView() {
        adapter.setListener(null)
        super.onDestroyView()
    }
}