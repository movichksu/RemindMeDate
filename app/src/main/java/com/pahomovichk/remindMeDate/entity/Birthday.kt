package com.pahomovichk.remindMeDate.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity
@TypeConverters(DateConverter::class)
data class Birthday (
        @PrimaryKey(autoGenerate = true)
        val birthdayID: Long,

        val birthdayName: String,

        val birthdayDate: LocalDate,

        val birthdayComments: String
        )
