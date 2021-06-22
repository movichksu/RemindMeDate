package com.pahomovichk.remindMeDate.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.YearlyEventsUseCase
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


class YearlyViewModel : ViewModel() {


    private val yearlyEventsUseCase: YearlyEventsUseCase by lazy { Dependencies.getYearlyEventUseCase() }

    private var _text = MutableLiveData<String>().apply {
        value = " "
    }
    val text: LiveData<String> = _text

    private var events = MutableLiveData<List<YearlyEvent>>(listOf())
    fun getYearlyEvents(): LiveData<List<YearlyEvent>>{
        return events
    }

    init{
        viewModelScope.launch {
                yearlyEventsUseCase.getEvents().collect {
                    //birthdaysList ->
                    //birthdays.value = birthdaysList.sortedBy { it.date }
                    events.value = it
            }
            }
    }

    fun addEvent(event: YearlyEvent) {
        viewModelScope.launch(Dispatchers.IO) {
                yearlyEventsUseCase.addEvent(event)
        }
    }

    fun editEvent(event: YearlyEvent) {
        viewModelScope.launch(Dispatchers.IO) {
                yearlyEventsUseCase.editEvent(event)
        }
    }

    fun onItemSelected(id: Long) =
            viewModelScope.launch(Dispatchers.IO) {
            yearlyEventsUseCase.deleteEvent(id)
        }
    fun cleanDb() =
        viewModelScope.launch(Dispatchers.IO) {
            yearlyEventsUseCase.cleanDb()
        }


}