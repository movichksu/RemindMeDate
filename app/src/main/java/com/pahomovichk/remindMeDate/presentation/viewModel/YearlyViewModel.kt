package com.pahomovichk.remindMeDate.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.WorkerUseCase
import com.pahomovichk.remindMeDate.domain.YearlyEventsUseCase
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.util.*


class YearlyViewModel : ViewModel() {

    private val workerUseCase: WorkerUseCase by lazy { Dependencies.getWorkerUseCase() }
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
        workerUseCase.yearlyNotificationRequest()
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

    fun homework(){
        val ids = listOf("aaa", "bbb", "ccc", "ddd")
        val random = Random()
        var list = mutableListOf<Operation>()
        for (i in 0..20){
            list.add(Operation(ids.get(random.nextInt(ids.size)),random.nextInt(10)+1))
        }
        val filterList = list
            .sortedByDescending {
                it.sum
            }
            .sortedBy {
                it.id
            }
            .log()
        val counter = list
            .groupingBy {
                it.sum
            }
            .eachCount()
        val max = counter.maxBy {
            it.value
        }?.key

        Log.d("OPERATION", "$max")
        Log.d("OPERATION", "--------------------------------------------------------------")
    }

    fun <T> List<T>.log(): List<T>{
        for(i in this.indices){
            Log.d("OPERATION", "$i : ${this.get(i)}")
        }
        return this
    }


}

class Operation(
    var id: String,
    var sum: Int
){
    override fun toString(): String {
        return "$id --> $sum"
    }

}