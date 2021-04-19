package com.pahomovichk.remindMeDate.ui.birthdays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BirthdaysViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No birthdays"
    }
    val text: LiveData<String> = _text
    //private var birthdays = MutableLiveData<List<Birthday>>(listOf())
}