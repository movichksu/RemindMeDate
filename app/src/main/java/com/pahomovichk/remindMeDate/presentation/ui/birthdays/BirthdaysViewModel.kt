package com.pahomovichk.remindMeDate.presentation.ui.birthdays

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.BirthdayUseCase
import com.pahomovichk.remindMeDate.entity.Birthday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BirthdaysViewModel : ViewModel() {


    private val birthdaysUseCase: BirthdayUseCase by lazy { Dependencies.getBirthdayUseCase() }
    //val birthday = Birthday()

    private val _text = MutableLiveData<String>().apply {
        value = "No birthdays"
    }
    val text: LiveData<String> = _text

    private var birthdays = MutableLiveData<List<Birthday>>(listOf())
    fun getBirthdays(): LiveData<List<Birthday>>{
        return birthdays
    }

    init{
        viewModelScope.launch {
                birthdaysUseCase.getBirthdays().collect { birthdaysList ->
                    birthdays.value = birthdaysList.sortedBy { it.date }
            }
            }
    }

    fun addBirthday() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(1000)
                //birthdaysUseCase.addBirthday(birthday)
            }
        }
    }

    fun onItemSelected(birthday: Birthday) =
        viewModelScope.launch {
            birthdaysUseCase.deleteBirthday(birthday)
        }


}