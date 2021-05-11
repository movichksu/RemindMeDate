package com.pahomovichk.remindMeDate.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.EventsUseCase
import com.pahomovichk.remindMeDate.domain.entity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsViewModel : ViewModel() {

    private val eventsUseCase: EventsUseCase by lazy { Dependencies.getEventsUseCase() }

    private val _text = MutableLiveData<String>().apply {
        value = "No dates"
    }
    val text: LiveData<String> = _text

    private var events = MutableLiveData<List<Event>>(listOf())
    fun getEvents(): LiveData<List<Event>>{
        return events
    }

    init{
        viewModelScope.launch {
            eventsUseCase.getEvents().collect {
                events.value = it
            }
        }
    }

    fun addEvent(event: Event) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                eventsUseCase.addEvent(event)
            }
        }
    }

    fun editEvent(event: Event) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                eventsUseCase.editEvent(event)
            }
        }
    }

    fun onItemSelected(id: Long) =
            viewModelScope.launch(Dispatchers.IO) {
                eventsUseCase.deleteEvent(id)
            }
}