package com.pahomovichk.remindMeDate.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity
@TypeConverters(DateConverter::class)
data class OnetimeEvent(
        @PrimaryKey(autoGenerate = true)
        val id: Long,

        val name: String,

        val type: String,

        val date: LocalDate,

        val comments: String
) : Event {
        override fun getEventName(): String {
                return name
        }

        override fun getEventType(): String {
                return type
        }
}