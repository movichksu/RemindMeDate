package com.pahomovichk.remindMeDate.data.db

import android.content.Context
import androidx.room.Room
import com.pahomovichk.remindMeDate.domain.BirthdayRepository
import com.pahomovichk.remindMeDate.entity.Birthday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDatabaseSource(
    context: Context
) : BirthdayRepository {
    private val birthdaysDatabase = Room.databaseBuilder(
        context,
        BirthdayDatabase::class.java,
        "personDataBase"
    )
        .build()

    override fun getBirthdays(): Flow<List<Birthday>> =
        birthdaysDatabase.getBirthdayDao().selectAll()

    override suspend fun deleteBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            birthdaysDatabase.getBirthdayDao().delete(birthday)
        }
    }

    override suspend fun addBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            birthdaysDatabase.getBirthdayDao().insert(birthday)
        }
    }

    override suspend fun editBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            birthdaysDatabase.getBirthdayDao().update(birthday)
        }
    }


}