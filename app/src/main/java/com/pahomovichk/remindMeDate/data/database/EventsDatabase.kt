package com.pahomovichk.remindMeDate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pahomovichk.remindMeDate.domain.entity.Birthday
import com.pahomovichk.remindMeDate.domain.entity.Event

@Database(entities = [Event::class, Birthday::class], version = 1)
abstract class EventsDatabase: RoomDatabase() {

    abstract fun getEventsDao(): OnetimeEventsDao
    abstract fun getBirthdaysDao(): YearlyEventsDao
}
