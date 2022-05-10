package com.example.myapplication.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    val allTasks: LiveData<List<Task>>
    val persTasks: LiveData<List<Task>>
    val workTasks: LiveData<List<Task>>
    val schTasks: LiveData<List<Task>>
    val repository: TaskRepository

    init {
        val dao = TaskDataBase.getDatabase(application).getTasksDao()
        repository = TaskRepository(dao)
        allTasks = repository.allTasks
        persTasks = repository.persTasks
        workTasks = repository.workTasks
        schTasks = repository.schTasks
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(task)
    }

    fun addTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }

    fun updateTask(task: Task, newStatus: String, id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(newStatus, id)
    }

}