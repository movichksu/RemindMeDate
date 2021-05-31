package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import kotlinx.coroutines.flow.Flow

interface YearlyEventsRepository {
    fun getYearlyEvents(): Flow<List<YearlyEvent>>
    suspend fun deleteYearlyEvent(event: YearlyEvent)
    suspend fun deleteYearlyEvent(id: Long)
    suspend fun addYearlyEvent(event: YearlyEvent)
    suspend fun editYearlyEvent(event: YearlyEvent)
    suspend fun cleanYearlyEventsDb()
}