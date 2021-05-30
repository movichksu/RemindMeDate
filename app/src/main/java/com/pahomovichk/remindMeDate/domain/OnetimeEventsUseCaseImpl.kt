package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.Event
import kotlinx.coroutines.flow.Flow

class OnetimeEventsUseCaseImpl(
        private val onetimeEventsRepository: OnetimeEventsRepository
): OnetimeEventsUseCase {
    override fun getEvents(): Flow<List<Event>> {
        return onetimeEventsRepository.getEvents()
    }

    override suspend fun deleteEvent(event: Event) {
        onetimeEventsRepository.deleteEvent(event)
    }

    override suspend fun deleteEvent(id: Long) {
        onetimeEventsRepository.deleteEvent(id)
    }

    override suspend fun addEvent(event: Event) {
        onetimeEventsRepository.addEvent(event)
    }

    override suspend fun editEvent(event: Event) {
        onetimeEventsRepository.editEvent(event)
    }

    override suspend fun cleanDb() {
        onetimeEventsRepository.cleanEventsDb()
    }
}