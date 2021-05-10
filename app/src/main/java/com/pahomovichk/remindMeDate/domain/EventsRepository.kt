package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.entity.Event
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun getEvents(): Flow<List<Event>>
    suspend fun deleteEvent(event: Event)
    suspend fun deleteEvent(id: Long)
    suspend fun addEvent(event: Event)
    suspend fun editEvent(event: Event)
    suspend fun cleanDb()
}