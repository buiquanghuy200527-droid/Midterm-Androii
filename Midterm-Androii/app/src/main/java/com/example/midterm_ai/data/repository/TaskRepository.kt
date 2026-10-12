package com.example.midterm_ai.data.repository

import com.example.midterm_ai.data.model.Task

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun addTask(title: String)
    suspend fun deleteTask(id: Int)
    suspend fun toggleDone(id: Int)
}
