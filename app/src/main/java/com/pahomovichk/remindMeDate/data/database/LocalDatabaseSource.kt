package com.pahomovichk.remindMeDate.data.database

import android.content.Context
import androidx.room.Room
import com.pahomovichk.remindMeDate.domain.YearlyEventsRepository
import com.pahomovichk.remindMeDate.domain.OnetimeEventsRepository
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDatabaseSource(
    context: Context
) : YearlyEventsRepository, OnetimeEventsRepository {
    private val database = Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            "eventsDatabase"
    ).build()

    override fun getYearlyEvents(): Flow<List<YearlyEvent>> =
        database.getYearlyEventsDao().selectAll()

    override suspend fun deleteYearlyEvent(event: YearlyEvent) {
        withContext(Dispatchers.IO) {
            database.getYearlyEventsDao().delete(event)
        }
    }

    override suspend fun deleteYearlyEvent(id: Long) {
        withContext(Dispatchers.IO) {
            database.getYearlyEventsDao().delete(id)
        }
    }

    override suspend fun addYearlyEvent(event: YearlyEvent) {
        withContext(Dispatchers.IO) {
            database.getYearlyEventsDao().insert(event)
        }
    }

    override suspend fun editYearlyEvent(event: YearlyEvent) {
        withContext(Dispatchers.IO) {
            database.getYearlyEventsDao().update(event)
        }
    }

    override suspend fun cleanYearlyEventsDb(){
        withContext(Dispatchers.IO) {
            database.getYearlyEventsDao().deleteAll()
        }
    }

    override fun getOnetimeEvents(): Flow<List<OnetimeEvent>> =
            database.getOnetimeEventsDao().selectAll()

    override suspend fun deleteOnetimeEvent(event: OnetimeEvent) {
        withContext(Dispatchers.IO) {
            database.getOnetimeEventsDao().delete(event)
        }
    }

    override suspend fun deleteOnetimeEvent(id: Long) {
        withContext(Dispatchers.IO) {
            database.getOnetimeEventsDao().delete(id)
        }
    }

    override suspend fun addOnetimeEvent(event: OnetimeEvent) {
        withContext(Dispatchers.IO) {
            database.getOnetimeEventsDao().insert(event)
        }
    }

    override suspend fun editOnetimeEvent(event: OnetimeEvent) {
        withContext(Dispatchers.IO) {
            database.getOnetimeEventsDao().update(event)
        }
    }

    override suspend fun cleanOnetimeEventsDb() {
        database.getOnetimeEventsDao().deleteAll()
    }


}