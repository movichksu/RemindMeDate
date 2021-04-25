package com.pahomovichk.remindMeDate.data.db

import androidx.room.*
import com.pahomovichk.remindMeDate.entity.Birthday
import kotlinx.coroutines.flow.Flow

@Dao
interface BirthdayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(birthday: Birthday)

    @Delete
    fun delete(birthday: Birthday)

    @Update
    fun update(birthday:Birthday)

    @Query("Select * From Birthday")
    fun selectAll(): Flow<List<Birthday>>

}