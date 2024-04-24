package com.example.roomexample2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Student::class], version = 1)
abstract class StudentDataBase :RoomDatabase(){


    // To delete all content and repopulate the database whenever the app is created, you'll create a RoomDatabase.Callback and override onCreate(). Because you cannot do Room
    // database operations on the UI thread, onCreate() launches a coroutine on the IO Dispatcher. Here I don't have to repopulate database, we will only delete all data.
    private class StudentsDatabaseCallback(private val scope:CoroutineScope):Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            if (INSTANCE != null){
                scope.launch {
                    INSTANCE!!.studentDao().deleteAll()
                }
            }
        }

    }


    abstract fun studentDao():StudentDao

    companion object{
        @Volatile
        private var INSTANCE : StudentDataBase? =null
        fun getDatabase(context: Context, scope:CoroutineScope): StudentDataBase{
            if(INSTANCE==null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDataBase::class.java,
                        "studentDatabase"
                    ).addCallback(StudentsDatabaseCallback(scope)).build()  //  add the callback to the database build sequence right before calling .build()
                }
            }
            return INSTANCE!!
        }

    }
}