package com.pahomovichk.remindMeDate.entity

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

class BirthdayConverter {

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String{
        return date.toString()
    }
    @TypeConverter
    fun toLocalDate(date: String): LocalDate{
        return LocalDate.parse(date)
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String{
        return time.toString()
    }
    @TypeConverter
    fun toLocalTime(time: String): LocalTime{
        return LocalTime.parse(time)
    }

}