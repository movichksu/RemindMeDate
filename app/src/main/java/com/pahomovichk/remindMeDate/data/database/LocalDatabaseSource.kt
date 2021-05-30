package com.pahomovichk.remindMeDate.data.database

import android.content.Context
import androidx.room.Room
import com.pahomovichk.remindMeDate.domain.BirthdayRepository
import com.pahomovichk.remindMeDate.domain.EventsRepository
import com.pahomovichk.remindMeDate.domain.entity.Birthday
import com.pahomovichk.remindMeDate.domain.entity.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDatabaseSource(
    context: Context
) : BirthdayRepository, EventsRepository {
    private val eventsDatabase = Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            "eventsDataBase"
    ).build()

    override fun getBirthdays(): Flow<List<Birthday>> =
        eventsDatabase.getBirthdaysDao().selectAllBirthdays()

    override suspend fun deleteBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getBirthdaysDao().deleteBirthday(birthday)
        }
    }

    override suspend fun deleteBirthday(id: Long) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getBirthdaysDao().deleteBirthday(id)
        }
    }

    override suspend fun addBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getBirthdaysDao().insertBirthday(birthday)
        }
    }

    override suspend fun editBirthday(birthday: Birthday) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getBirthdaysDao().updateBirthday(birthday)
        }
    }

    override suspend fun cleanBirthdaysDb(){
        withContext(Dispatchers.IO) {
            eventsDatabase.getBirthdaysDao().deleteAllBirthdays()
        }
    }

    override fun getEvents(): Flow<List<Event>> =
            eventsDatabase.getEventsDao().selectAllEvents()

    override suspend fun deleteEvent(event: Event) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getEventsDao().deleteEvent(event)
        }
    }

    override suspend fun deleteEvent(id: Long) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getEventsDao().deleteEvent(id)
        }
    }

    override suspend fun addEvent(event: Event) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getEventsDao().insertEvent(event)
        }
    }

    override suspend fun editEvent(event: Event) {
        withContext(Dispatchers.IO) {
            eventsDatabase.getEventsDao().updateEvent(event)
        }
    }

    override suspend fun cleanEventsDb() {
        eventsDatabase.getEventsDao().deleteAllEvents()
    }


}