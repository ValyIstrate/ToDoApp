package com.example.myapplication.model

import androidx.lifecycle.LiveData

class TaskRepository(private val tasksDao: TasksDao) {
    val allTasks: LiveData<List<Task>> = tasksDao.getAllTasks()
    val persTasks: LiveData<List<Task>> = tasksDao.select("Personal")
    val workTasks: LiveData<List<Task>> = tasksDao.select("Work")
    val schTasks: LiveData<List<Task>> = tasksDao.select("School")

    suspend fun insert(task: Task) {
        tasksDao.insert(task)
    }

    suspend fun delete(task: Task) {
        tasksDao.delete(task)
    }

    suspend fun update(task: Task) {
        tasksDao.update(task)
    }

    fun update(newStatus: String, id: Int) {
        tasksDao.update(newStatus, id)
    }

    fun select(chosenType: String) {
        tasksDao.select(chosenType)
    }
}