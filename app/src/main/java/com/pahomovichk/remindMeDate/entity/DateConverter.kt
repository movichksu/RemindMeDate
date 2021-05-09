package com.pahomovichk.remindMeDate.entity

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

class DateConverter {

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String{
        return date.toString()
    }
    @TypeConverter
    fun toLocalDate(date: String): LocalDate{
        return LocalDate.parse(date)
    }

}