package com.pahomovichk.remindMeDate.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DatesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "No dates"
    }
    val text: LiveData<String> = _text
}