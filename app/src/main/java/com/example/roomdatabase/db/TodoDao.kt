package com.example.roomdatabase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase.fragment.model.TodoManager

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(todo: TodoManager)

    @Update
    suspend fun updateUser(user:TodoManager)


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<TodoManager>>

    @Delete
    suspend fun deleteUser(user: TodoManager)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()


   // fun readAllData():LiveData<List<TodoManager>>

   /* @Query("Delete FROM TodoManager where id = :id")
    fun deleteTodo(id: Int)
   */
    /*@Update
    fun updateTodo(todo: TodoManager)
*/
}