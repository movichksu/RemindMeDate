package com.pahomovichk.remindMeDate.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.BirthdayUseCase
import com.pahomovichk.remindMeDate.domain.entity.Birthday
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


class BirthdaysViewModel : ViewModel() {


    private val birthdaysUseCase: BirthdayUseCase by lazy { Dependencies.getBirthdayUseCase() }

    private var _text = MutableLiveData<String>().apply {
        value = "No birthdays"
    }
    val text: LiveData<String> = _text

    private var birthdays = MutableLiveData<List<Birthday>>(listOf())
    fun getBirthdays(): LiveData<List<Birthday>>{
        return birthdays
    }

    init{
        viewModelScope.launch {
                birthdaysUseCase.getBirthdays().collect {
                    //birthdaysList ->
                    //birthdays.value = birthdaysList.sortedBy { it.date }
                    birthdays.value = it
            }
            }
    }

    fun addBirthday(birthday: Birthday) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                birthdaysUseCase.addBirthday(birthday)
            }
        }
    }

    fun editBirthday(birthday: Birthday) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                birthdaysUseCase.editBirthday(birthday)
            }
        }
    }

    fun onItemSelected(id: Long) =
            viewModelScope.launch(Dispatchers.IO) {
            birthdaysUseCase.deleteBirthday(id)
        }


}