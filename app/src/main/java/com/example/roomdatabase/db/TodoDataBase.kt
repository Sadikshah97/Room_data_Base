package com.example.roomdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomdatabase.Converters
import com.example.roomdatabase.fragment.model.TodoManager

@Database(entities = [TodoManager::class] ,version=1, exportSchema = false)
@TypeConverters(Converters::class)  // Register the converters here
abstract class TodoDataBase:RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE:TodoDataBase?=null
        var NAME="Todo_DB"
        fun getDatabase(context: Context):TodoDataBase{
            val tempInstace= INSTANCE
            if(tempInstace!=null){
                return tempInstace
            }
           synchronized(this){
               val instance =Room.databaseBuilder(
                   context.applicationContext,
                   TodoDataBase::class.java,
                   name = "user_database"
               ).build()
               INSTANCE = instance
               return instance
           }
        }

    }
    abstract fun getTodoDao():TodoDao
}

