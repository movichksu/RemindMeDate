package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.entity.Birthday
import kotlinx.coroutines.flow.Flow

interface BirthdayRepository {
    fun getBirthdays(): Flow<List<Birthday>>
    suspend fun deleteBirthday(birthday: Birthday)
    suspend fun addBirthday(birthday: Birthday)
    suspend fun editBirthday(birthday: Birthday)

}