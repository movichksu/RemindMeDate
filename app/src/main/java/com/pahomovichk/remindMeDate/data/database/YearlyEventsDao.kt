package com.pahomovichk.remindMeDate.data.database

import androidx.room.*
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface YearlyEventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: YearlyEvent)

    @Delete
    fun delete(event: YearlyEvent)

    @Update
    fun update(event: YearlyEvent)

    @Query("Select * From YearlyEvent")
    fun selectAll(): Flow<List<YearlyEvent>>

    @Query("Select * From YearlyEvent")
    fun selectAllOnce(): List<YearlyEvent>

    @Query("Delete From YearlyEvent")
    fun deleteAll()

    @Query("Delete From YearlyEvent Where id = :id")
    fun delete(id: Long)
}