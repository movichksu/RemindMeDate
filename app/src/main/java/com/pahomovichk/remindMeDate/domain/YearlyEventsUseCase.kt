package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.Birthday
import kotlinx.coroutines.flow.Flow

interface YearlyEventsUseCase {
    fun getBirthdays(): Flow<List<Birthday>>
    suspend fun deleteBirthday(birthday: Birthday)
    suspend fun deleteBirthday(id: Long)
    suspend fun addBirthday(birthday: Birthday)
    suspend fun editBirthday(birthday: Birthday)
    suspend fun cleanDb()
}