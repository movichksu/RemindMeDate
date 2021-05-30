package com.pahomovichk.remindMeDate

import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.data.database.LocalDatabaseSource
import com.pahomovichk.remindMeDate.domain.*

object Dependencies {
    private val YEARLY_EVENTS_REPOSITORY: YearlyEventsRepository by lazy {LocalDatabaseSource(App.instance)}
    private val ONETIME_EVENTS_REPOSITORY: OnetimeEventsRepository by lazy{LocalDatabaseSource(App.instance)}

    private fun getBirthdaysRepository(): YearlyEventsRepository{
        return YEARLY_EVENTS_REPOSITORY
    }
    private fun getEventRepository(): OnetimeEventsRepository{
        return ONETIME_EVENTS_REPOSITORY
    }

    fun getBirthdayUseCase(): YearlyEventsUseCase{
        return YearlyEventsUseCaseImpl(getBirthdaysRepository())
    }
    fun getEventsUseCase(): OnetimeEventsUseCase{
        return OnetimeEventsUseCaseImpl(getEventRepository())
    }
}