package com.pahomovichk.remindMeDate.ui.birthdays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pahomovichk.remindMeDate.R

class BirthdaysFragment : Fragment() {

    private lateinit var birthdaysViewModel: BirthdaysViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        birthdaysViewModel =
                ViewModelProvider(this).get(BirthdaysViewModel::class.java)
        val root = inflater.inflate(R.layout.birthdays_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.birthdays_text)
        birthdaysViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}