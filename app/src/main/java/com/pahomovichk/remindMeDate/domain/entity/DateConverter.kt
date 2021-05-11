package com.pahomovichk.remindMeDate.domain.entity

import androidx.room.TypeConverter
import java.time.LocalDate

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