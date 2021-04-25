package com.pahomovichk.remindMeDate

import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.data.db.LocalDatabaseSource
import com.pahomovichk.remindMeDate.domain.BirthdayRepository
import com.pahomovichk.remindMeDate.domain.BirthdayUseCase
import com.pahomovichk.remindMeDate.domain.BirthdayUseCaseImpl

object Dependencies {
    private val birthdayRepository: BirthdayRepository by lazy {LocalDatabaseSource(App.instance)}

    private fun getBirthdaysRepository(): BirthdayRepository{
        return birthdayRepository
    }

    fun getBirthdayUseCase(): BirthdayUseCase{
        return BirthdayUseCaseImpl(getBirthdaysRepository())
    }
}