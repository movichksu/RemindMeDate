package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.domain.entity.Birthday
import kotlinx.coroutines.flow.Flow

class YearlyEventsUseCaseImpl(
    private val yearlyEventsRepository: YearlyEventsRepository
) : YearlyEventsUseCase {
    override fun getBirthdays(): Flow<List<Birthday>> {
        return yearlyEventsRepository.getBirthdays()
    }

    override suspend fun deleteBirthday(birthday: Birthday) {
        yearlyEventsRepository.deleteBirthday(birthday)
    }

    override suspend fun deleteBirthday(id: Long) {
        yearlyEventsRepository.deleteBirthday(id)
    }

    override suspend fun addBirthday(birthday: Birthday) {
        yearlyEventsRepository.addBirthday(birthday)
    }

    override suspend fun editBirthday(birthday: Birthday) {
        yearlyEventsRepository.editBirthday(birthday)
    }

    override suspend fun cleanDb() {
        yearlyEventsRepository.cleanBirthdaysDb()
    }


}