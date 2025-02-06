package com.example.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase.db.TodoDao
import com.example.roomdatabase.fragment.model.TodoManager

class UserRepository(private val userDao:TodoDao) {
    val readAllData:LiveData<List<TodoManager>> = userDao.readAllData()
     suspend fun addUser(user: TodoManager){
         userDao.addUser(user)

     }
    suspend fun updateUser(user: TodoManager){
        userDao.updateUser(user)
    }
    suspend fun deleteuser(user: TodoManager){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }
}