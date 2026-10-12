package com.example.midterm_ai.data.repository

import com.example.midterm_ai.data.local.TaskDao
import com.example.midterm_ai.data.local.TaskEntity
import com.example.midterm_ai.data.model.Task

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks().map { it.toDomainModel() }
    }

    override suspend fun addTask(title: String) {
        if (title.isBlank()) return
        taskDao.insertTask(TaskEntity(title = title.trim(), isDone = false))
    }

    override suspend fun deleteTask(id: Int) {
        taskDao.deleteTaskById(id)
    }

    override suspend fun toggleDone(id: Int) {
        val existing = taskDao.getTaskById(id) ?: return
        taskDao.updateTask(existing.copy(isDone = !existing.isDone))
    }
}
