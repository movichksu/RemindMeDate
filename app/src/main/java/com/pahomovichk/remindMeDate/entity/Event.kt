package com.pahomovichk.remindMeDate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity
@TypeConverters(DateConverter::class)
data class Event(
        @PrimaryKey(autoGenerate = true)
        val eventID: Long,

        val eventName: String,

        val eventDate: LocalDate,

        val eventComment: String
)