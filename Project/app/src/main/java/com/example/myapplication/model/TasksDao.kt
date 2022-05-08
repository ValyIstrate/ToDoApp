package com.example.myapplication.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("Select * from tasksTable order by taskId ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("Select * from tasksTable Where taskId == :id")
    fun get(id: Long): LiveData<Task>

    @Query("Delete from tasksTable")
    fun deleteAll()

}