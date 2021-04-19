package com.pahomovichk.remindMeDate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate
import java.time.LocalTime

@Entity
@TypeConverters(BirthdayConverter::class)
data class Birthday (
        @PrimaryKey(autoGenerate = true)
        val id: Long,

        val name: String,

        val date: LocalDate,

        val time: LocalTime
        )
