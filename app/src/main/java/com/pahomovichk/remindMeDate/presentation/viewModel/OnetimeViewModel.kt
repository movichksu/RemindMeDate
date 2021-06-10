package com.pahomovichk.remindMeDate.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.OnetimeEventsUseCase
import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnetimeViewModel : ViewModel() {

    private val onetimeEventsUseCase: OnetimeEventsUseCase by lazy { Dependencies.getOnetimeEventUseCase() }

    private val _text = MutableLiveData<String>().apply {
        value = " "
    }
    val text: LiveData<String> = _text

    private var events = MutableLiveData<List<OnetimeEvent>>(listOf())
    fun getOnetimeEvents(): LiveData<List<OnetimeEvent>>{
        return events
    }

    init{
        viewModelScope.launch {
            onetimeEventsUseCase.getEvents().collect {
                events.value = it
            }
        }
    }

    fun addEvent(event: OnetimeEvent) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                onetimeEventsUseCase.addEvent(event)
            }
        }
    }

    fun editEvent(event: OnetimeEvent) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                onetimeEventsUseCase.editEvent(event)
            }
        }
    }

    fun onItemSelected(id: Long) =
            viewModelScope.launch(Dispatchers.IO) {
                onetimeEventsUseCase.deleteEvent(id)
            }
}