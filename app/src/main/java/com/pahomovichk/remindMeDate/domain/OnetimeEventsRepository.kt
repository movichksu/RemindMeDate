package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import kotlinx.coroutines.flow.Flow

interface OnetimeEventsRepository {
    fun getOnetimeEvents(): Flow<List<OnetimeEvent>>
    suspend fun deleteOnetimeEvent(event: OnetimeEvent)
    suspend fun deleteOnetimeEvent(id: Long)
    suspend fun addOnetimeEvent(event: OnetimeEvent)
    suspend fun editOnetimeEvent(event: OnetimeEvent)
    suspend fun cleanOnetimeEventsDb()
}