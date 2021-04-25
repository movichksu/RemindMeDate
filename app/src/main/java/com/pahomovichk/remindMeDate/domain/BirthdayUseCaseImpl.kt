package com.pahomovichk.remindMeDate.domain

import com.pahomovichk.remindMeDate.entity.Birthday
import kotlinx.coroutines.flow.Flow

class BirthdayUseCaseImpl(
    private val birthdayRepository: BirthdayRepository
) : BirthdayUseCase {
    override fun getBirthdays(): Flow<List<Birthday>> {
        return birthdayRepository.getBirthdays()
    }

    override suspend fun deleteBirthday(birthday: Birthday) {
        birthdayRepository.deleteBirthday(birthday)
    }

    override suspend fun addBirthday(birthday: Birthday) {
        birthdayRepository.addBirthday(birthday)
    }

    override suspend fun editBirthday(birthday: Birthday) {
        birthdayRepository.editBirthday(birthday)
    }


}