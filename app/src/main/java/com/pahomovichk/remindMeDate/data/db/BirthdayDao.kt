package com.pahomovichk.remindMeDate.data.db

import androidx.room.*
import com.pahomovichk.remindMeDate.entity.Birthday
import kotlinx.coroutines.flow.Flow

@Dao
interface BirthdayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBirthday(birthday: Birthday)

    @Delete
    fun deleteBirthday(birthday: Birthday)

    @Update
    fun updateBirthday(birthday:Birthday)

    @Query("Select * From Birthday")
    fun selectAllBirthdays(): Flow<List<Birthday>>

    @Query("Delete From Birthday")
    fun deleteAllBirthdays()

}