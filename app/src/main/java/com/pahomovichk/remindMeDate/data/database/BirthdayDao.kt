package com.pahomovichk.remindMeDate.data.database

import androidx.room.*
import com.pahomovichk.remindMeDate.domain.entity.Birthday
import kotlinx.coroutines.flow.Flow

@Dao
interface BirthdayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBirthday(birthday: Birthday)

    @Delete
    fun deleteBirthday(birthday: Birthday)

    @Update
    fun updateBirthday(birthday: Birthday)

    @Query("Select * From Birthday")
    fun selectAllBirthdays(): Flow<List<Birthday>>

    @Query("Delete From Birthday")
    fun deleteAllBirthdays()

    @Query("Delete From Birthday Where id = :id")
    fun deleteBirthday(id: Long)
}