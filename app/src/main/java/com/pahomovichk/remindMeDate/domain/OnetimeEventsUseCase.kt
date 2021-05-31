package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import kotlinx.coroutines.flow.Flow

interface OnetimeEventsUseCase {
    fun getEvents(): Flow<List<OnetimeEvent>>
    suspend fun deleteEvent(event: OnetimeEvent)
    suspend fun deleteEvent(id: Long)
    suspend fun addEvent(event: OnetimeEvent)
    suspend fun editEvent(event: OnetimeEvent)
    suspend fun cleanDb()
}