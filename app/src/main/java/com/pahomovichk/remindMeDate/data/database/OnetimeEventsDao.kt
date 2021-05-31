package com.pahomovichk.remindMeDate.data.database

import androidx.room.*
import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface OnetimeEventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: OnetimeEvent)

    @Delete
    fun delete(event: OnetimeEvent)

    @Update
    fun update(event: OnetimeEvent)

    @Query("Select * From OnetimeEvent")
    fun selectAll(): Flow<List<OnetimeEvent>>

    @Query("Delete From OnetimeEvent")
    fun deleteAll()

    @Query("Delete From OnetimeEvent Where id = :id")
    fun delete(id: Long)
}