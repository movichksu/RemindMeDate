package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.Event
import kotlinx.coroutines.flow.Flow

class EventsUseCaseImpl(
        private val eventsRepository: EventsRepository
): EventsUseCase {
    override fun getEvents(): Flow<List<Event>> {
        return eventsRepository.getEvents()
    }

    override suspend fun deleteEvent(event: Event) {
        eventsRepository.deleteEvent(event)
    }

    override suspend fun deleteEvent(id: Long) {
        eventsRepository.deleteEvent(id)
    }

    override suspend fun addEvent(event: Event) {
        eventsRepository.addEvent(event)
    }

    override suspend fun editEvent(event: Event) {
        eventsRepository.editEvent(event)
    }

    override suspend fun cleanDb() {
        eventsRepository.cleanEventsDb()
    }
}