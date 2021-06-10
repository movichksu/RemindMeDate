package com.pahomovichk.remindMeDate.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.R

class SettingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = App.instance.getString(R.string.app_version)
    }
    val text: LiveData<String> = _text
}