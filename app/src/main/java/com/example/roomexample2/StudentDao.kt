package com.example.roomexample2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(students: Student)

    @Query("DELETE FROM students")
    fun deleteAll()

    @Query("SELECT * FROM students")
    fun getAllData(): Flow<List<Student>>
}