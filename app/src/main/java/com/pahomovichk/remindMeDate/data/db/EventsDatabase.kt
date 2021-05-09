package com.pahomovichk.remindMeDate.data.db

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.pahomovichk.remindMeDate.entity.Birthday

@Database(entities = [Entity::class], version = 1)
abstract class EventsDatabase: RoomDatabase() {

    abstract fun getEventsDao(): EventsDao
}
