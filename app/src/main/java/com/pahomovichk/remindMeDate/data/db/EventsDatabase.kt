package com.pahomovichk.remindMeDate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pahomovichk.remindMeDate.entity.Event

@Database(entities = [Event::class], version = 1)
abstract class EventsDatabase: RoomDatabase() {

    abstract fun getEventsDao(): EventsDao
}
