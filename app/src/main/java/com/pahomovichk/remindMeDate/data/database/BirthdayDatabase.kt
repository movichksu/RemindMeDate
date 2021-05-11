package com.pahomovichk.remindMeDate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pahomovichk.remindMeDate.domain.entity.Birthday

@Database(entities = [Birthday::class], version = 1)
abstract class BirthdayDatabase: RoomDatabase() {

    abstract fun getBirthdayDao(): BirthdayDao
}