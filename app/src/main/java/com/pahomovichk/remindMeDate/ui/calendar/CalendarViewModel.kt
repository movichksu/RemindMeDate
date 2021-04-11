package com.pahomovichk.remindMeDate.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dates Fragment"
    }
    val text: LiveData<String> = _text
}