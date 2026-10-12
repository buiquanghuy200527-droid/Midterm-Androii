package com.example.midterm_ai.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.midterm_ai.data.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false
) {
    fun toDomainModel() = Task(id = id, title = title, isDone = isDone)

    companion object {
        fun fromDomainModel(task: Task) = TaskEntity(id = task.id, title = task.title, isDone = task.isDone)
    }
}
