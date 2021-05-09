package com.pahomovichk.remindMeDate

import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.data.db.LocalDatabaseSource
import com.pahomovichk.remindMeDate.domain.*

object Dependencies {
    private val birthdayRepository: BirthdayRepository by lazy {LocalDatabaseSource(App.instance)}
    private val eventsRepository: EventsRepository by lazy{LocalDatabaseSource(App.instance)}

    private fun getBirthdaysRepository(): BirthdayRepository{
        return birthdayRepository
    }
    private fun getEventRepository(): EventsRepository{
        return eventsRepository
    }

    fun getBirthdayUseCase(): BirthdayUseCase{
        return BirthdayUseCaseImpl(getBirthdaysRepository())
    }
    fun getEventsUseCase(): EventsUseCase{
        return EventsUseCaseImpl(getEventRepository())
    }
}