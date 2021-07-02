package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import kotlinx.coroutines.flow.Flow

interface YearlyEventsUseCase {
    fun getEvents(): Flow<List<YearlyEvent>>
    suspend fun getAllEvents(): List<YearlyEvent>
    suspend fun deleteEvent(event: YearlyEvent)
    suspend fun deleteEvent(id: Long)
    suspend fun addEvent(event: YearlyEvent)
    suspend fun editEvent(event: YearlyEvent)
    suspend fun cleanDb()
}