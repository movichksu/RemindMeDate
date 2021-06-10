package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import kotlinx.coroutines.flow.Flow

class OnetimeEventsUseCaseImpl(
        private val onetimeEventsRepository: OnetimeEventsRepository
): OnetimeEventsUseCase {
    override fun getEvents(): Flow<List<OnetimeEvent>> {
        return onetimeEventsRepository.getOnetimeEvents()
    }

    override suspend fun deleteEvent(event: OnetimeEvent) {
        onetimeEventsRepository.deleteOnetimeEvent(event)
    }

    override suspend fun deleteEvent(id: Long) {
        onetimeEventsRepository.deleteOnetimeEvent(id)
    }

    override suspend fun addEvent(event: OnetimeEvent) {
        onetimeEventsRepository.addOnetimeEvent(event)
    }

    override suspend fun editEvent(event: OnetimeEvent) {
        onetimeEventsRepository.editOnetimeEvent(event)
    }

    override suspend fun cleanDb() {
        onetimeEventsRepository.cleanOnetimeEventsDb()
    }
}