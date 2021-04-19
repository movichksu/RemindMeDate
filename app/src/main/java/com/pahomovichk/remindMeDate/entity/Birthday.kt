package com.pahomovichk.remindMeDate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class Birthday (
        @PrimaryKey(autoGenerate = true)
        val id: Long,

        val name: String,

        val date: LocalDate,

        val time: LocalTime
        )
