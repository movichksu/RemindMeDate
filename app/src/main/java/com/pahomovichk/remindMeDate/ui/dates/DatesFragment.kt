package com.pahomovichk.remindMeDate.ui.dates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.R

class DatesFragment : Fragment() {

    private lateinit var datesViewModel: DatesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        datesViewModel =
                ViewModelProvider(this).get(DatesViewModel::class.java)
        val root = inflater.inflate(R.layout.dates_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.dates_text)
        datesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}