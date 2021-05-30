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
import com.pahomovichk.remindMeDate.domain.entity.Birthday
import com.pahomovichk.remindMeDate.presentation.adapter.YearlyEventAdapter
import com.pahomovichk.remindMeDate.presentation.adapter.YearlyEventClickListener
import com.pahomovichk.remindMeDate.presentation.viewModel.YearlyViewModel


class YearlyFragment : Fragment(), YearlyEventClickListener {

    companion object {
        fun newInstance() =
            YearlyFragment()
    }

    private lateinit var viewModel: YearlyViewModel

    private lateinit var birthdaysList: RecyclerView
    private var adapter = YearlyEventAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)
        val root = inflater.inflate(R.layout.yearly_events_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.birthdays_text)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            textView.setVisibility(INVISIBLE)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        birthdaysList = view.findViewById(R.id.persons_list)
        birthdaysList.layoutManager = LinearLayoutManager(requireContext())
        birthdaysList.adapter = adapter
        adapter.setListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(YearlyViewModel::class.java)

        viewModel.getBirthdays().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }

    override fun onClick(birthday: Birthday) {
        val intent = Intent(this.context, YearlyItemActivity::class.java)
        intent.putExtra(Constants.ID, birthday.id)
        intent.putExtra(Constants.NAME, birthday.name)
        intent.putExtra(Constants.DATE, "${birthday.date.format(Constants.gettingLocalFormatter)}")
        intent.putExtra(Constants.COMMENT, birthday.comments)
        startActivity(intent)
    }

    override fun onDestroyView() {
        adapter.setListener(null)
        super.onDestroyView()
    }
}