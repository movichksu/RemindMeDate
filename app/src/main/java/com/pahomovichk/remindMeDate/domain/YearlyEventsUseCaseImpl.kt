package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import kotlinx.coroutines.flow.Flow

class YearlyEventsUseCaseImpl(
    private val yearlyEventsRepository: YearlyEventsRepository
) : YearlyEventsUseCase {
    override fun getEvents(): Flow<List<YearlyEvent>> {
        return yearlyEventsRepository.getYearlyEvents()
    }

    override suspend fun getAllEvents(): List<YearlyEvent>{
        return yearlyEventsRepository.getAllYearlyEvents()
    }

    override suspend fun deleteEvent(event: YearlyEvent) {
        yearlyEventsRepository.deleteYearlyEvent(event)
    }

    override suspend fun deleteEvent(id: Long) {
        yearlyEventsRepository.deleteYearlyEvent(id)
    }

    override suspend fun addEvent(event: YearlyEvent) {
        yearlyEventsRepository.addYearlyEvent(event)
    }

    override suspend fun editEvent(event: YearlyEvent) {
        yearlyEventsRepository.editYearlyEvent(event)
    }

    override suspend fun cleanDb() {
        yearlyEventsRepository.cleanYearlyEventsDb()
    }


}