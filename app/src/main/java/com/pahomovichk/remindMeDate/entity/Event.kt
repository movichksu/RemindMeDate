package com.pahomovichk.remindMeDate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity
@TypeConverters(DateConverter::class)
data class Event(
        @PrimaryKey(autoGenerate = true)
        val id: Long,

        val name: String,

        val date: LocalDate,

        val comments: String
)