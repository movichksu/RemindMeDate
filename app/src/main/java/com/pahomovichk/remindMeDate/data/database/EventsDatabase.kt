package com.pahomovichk.remindMeDate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent

@Database(entities = [OnetimeEvent::class, YearlyEvent::class], version = 1)
abstract class EventsDatabase: RoomDatabase() {

    abstract fun getOnetimeEventsDao(): OnetimeEventsDao
    abstract fun getYearlyEventsDao(): YearlyEventsDao
}
