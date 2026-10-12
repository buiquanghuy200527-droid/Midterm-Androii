package com.example.midterm_ai.data.repository

import com.example.midterm_ai.data.model.Task

class FakeTaskRepository : TaskRepository {
    private val tasks = mutableListOf<Task>()
    private var currentId = 1

    override suspend fun getAllTasks(): List<Task> {
        return tasks.toList()
    }

    override suspend fun addTask(title: String) {
        if (title.isBlank()) return
        tasks.add(0, Task(id = currentId++, title = title.trim(), isDone = false))
    }

    override suspend fun deleteTask(id: Int) {
        tasks.removeAll { it.id == id }
    }

    override suspend fun toggleDone(id: Int) {
        val index = tasks.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = tasks[index]
            tasks[index] = item.copy(isDone = !item.isDone)
        }
    }
}
