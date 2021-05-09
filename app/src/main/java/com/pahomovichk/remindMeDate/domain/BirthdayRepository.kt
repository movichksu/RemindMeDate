package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.entity.Birthday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface BirthdayRepository {
    fun getBirthdays(): Flow<List<Birthday>>
    suspend fun deleteBirthday(birthday: Birthday)
    suspend fun addBirthday(birthday: Birthday)
    suspend fun editBirthday(birthday: Birthday)
    suspend fun cleanDb()
}