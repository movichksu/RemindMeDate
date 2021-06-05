package com.pahomovichk.remindMeDate.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import com.pahomovichk.remindMeDate.presentation.adapter.YearlyEventAdapter
import com.pahomovichk.remindMeDate.presentation.adapter.YearlyEventClickListener
import com.pahomovichk.remindMeDate.presentation.viewModel.YearlyViewModel


class YearlyFragment : Fragment(), YearlyEventClickListener {

    companion object {
        fun newInstance() =
            YearlyFragment()
    }

    private lateinit var viewModel: YearlyViewModel

    private lateinit var eventsList: RecyclerView
    private var adapter = YearlyEventAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)
        val root = inflater.inflate(R.layout.yearly_events_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.yearly_events_text)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            textView.setVisibility(INVISIBLE)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsList = view.findViewById(R.id.yearly_events_list)
        eventsList.layoutManager = LinearLayoutManager(requireContext())
        eventsList.adapter = adapter
        adapter.setListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)

        viewModel.getYearlyEvents().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    override fun onClick(event: YearlyEvent) {
        val intent = Intent(this.context, YearlyItemActivity::class.java)
        intent.putExtra(Constants.ID, event.id)
        intent.putExtra(Constants.NAME, event.name)
        intent.putExtra(Constants.TYPE, event.type)
        intent.putExtra(Constants.DATE, "${event.date.format(Constants.gettingLocalFormatter)}")
        intent.putExtra(Constants.COMMENT, event.comments)
        startActivity(intent)
    }

    override fun onDestroyView() {
        adapter.setListener(null)
        super.onDestroyView()
    }
}