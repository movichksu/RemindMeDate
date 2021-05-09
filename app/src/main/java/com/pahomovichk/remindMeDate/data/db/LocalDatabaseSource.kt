package com.pahomovichk.remindMeDate.data.db

import android.content.Context
import androidx.room.Room
import com.pahomovichk.remindMeDate.domain.BirthdayRepository
import com.pahomovichk.remindMeDate.domain.EventsRepository
import com.pahomovichk.remindMeDate.entity.Birthday
import com.pahomovichk.remindMeDate.entity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDatabaseSource(
    context: Context
) : BirthdayRepository, EventsRepository {
    private val birthdaysDatabase = Room.databaseBuilder(
        context,
        BirthdayDatabase::class.java,
        "personDataBase"
    ).build()
    private val eventsDatabase = Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            "eventsDataBase"
    ).build()

    override fun getBirthdays(): Flow<List<Birthday>> =
        birthdaysDatabase.getBirthdayDao().selectAllBirthdays()

    override suspend fun deleteBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            birthdaysDatabase.getBirthdayDao().deleteBirthday(birthday)
        }
    }

    override suspend fun deleteBirthday(id: Long) {
        birthdaysDatabase.getBirthdayDao().deleteBirthday(id)
    }

    override suspend fun addBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            birthdaysDatabase.getBirthdayDao().insertBirthday(birthday)
        }
    }

    override suspend fun editBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            birthdaysDatabase.getBirthdayDao().updateBirthday(birthday)
        }
    }

    override suspend fun cleanBirthdaysDb(){
        withContext(Dispatchers.IO) {
            birthdaysDatabase.getBirthdayDao().deleteAllBirthdays()
        }
    }

    override fun getEvents(): Flow<List<Event>> =
            eventsDatabase.getEventsDao().selectAllEvents()

    override suspend fun deleteEvent(event: Event) {
        eventsDatabase.getEventsDao().deleteEvent(event)
    }

    override suspend fun deleteEvent(id: Long) {
        eventsDatabase.getEventsDao().deleteEvent(id)
    }

    override suspend fun addEvent(event: Event) {
        eventsDatabase.getEventsDao().insertEvent(event)
    }

    override suspend fun editEvent(event: Event) {
        eventsDatabase.getEventsDao().updateEvent(event)
    }

    override suspend fun cleanEventsDb() {
        eventsDatabase.getEventsDao().deleteAllEvents()
    }


}