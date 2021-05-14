package com.pahomovichk.remindMeDate.data.database

import androidx.room.*
import com.pahomovichk.remindMeDate.domain.entity.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)

    @Update
    fun updateEvent(event: Event)

    @Query("Select * From Event")
    fun selectAllEvents(): Flow<List<Event>>

    @Query("Delete From Event")
    fun deleteAllEvents()

    @Query("Delete From Event Where id = :id")
    fun deleteEvent(id: Long)
}