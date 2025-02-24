package com.example.roomdatabase.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.repository.UserRepository
import com.example.roomdatabase.db.TodoDataBase
import com.example.roomdatabase.fragment.model.TodoManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class  TodoViewModel(application: Application):AndroidViewModel(application) {
    private val repository: UserRepository

    val readAllData : LiveData<List<TodoManager>>
    init {
        val userDao=TodoDataBase.getDatabase(application).getTodoDao()
             repository= UserRepository(userDao)
             readAllData=repository.readAllData
    }

    fun addUser(user: TodoManager){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }

    }
    fun updateUser(user: TodoManager){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }

    }
     fun deleteuser(user: TodoManager){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteuser(user)
        }
    }
     fun deleteAllUser(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllUser()
        }
    }


}