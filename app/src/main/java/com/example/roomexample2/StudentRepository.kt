package com.example.roomexample2

import androidx.annotation.WorkerThread

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class StudentRepository(private val studentDao:StudentDao) {
    // we use this companion object so that only one object is being created.
    companion object{
        @Volatile
        private var INSTANCE: StudentRepository? =null
        fun getInstance(studentDao: StudentDao):StudentRepository{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE= StudentRepository(studentDao)
                }
            }
            return INSTANCE!!
        }
    }
    // The list of students is a public property. It's initialized by getting the Flow list of students from Room; you can do this because of how you defined the getAll method to return Flow . Room executes all queries on a separate thread.
    val allStudents=studentDao.getAllData()

    @WorkerThread
    suspend fun insert(student: Student){
        studentDao.insertData(student)
    }
}