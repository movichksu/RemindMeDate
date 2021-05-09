package com.pahomovichk.remindMeDate.entity

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Entity
@TypeConverters(BirthdayConverter::class)
data class Birthday (
        @PrimaryKey(autoGenerate = true)
        val id: Long,

        @Nullable
        val name: String,

        @Nullable
        val date: LocalDate,

        @Nullable
        val comments: String
        )
