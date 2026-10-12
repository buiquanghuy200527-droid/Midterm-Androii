package com.example.midterm_ai

import com.example.midterm_ai.data.repository.FakeTaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TaskRepositoryTest {

    private lateinit var fakeRepository: FakeTaskRepository

    @Before
    fun setUp() {
        fakeRepository = FakeTaskRepository()
    }

    @Test
    fun addTask_increasesListSize() = runBlocking {
        fakeRepository.addTask("Học bài Android")
        val tasks = fakeRepository.getAllTasks()
        assertEquals(1, tasks.size)
        assertEquals("Học bài Android", tasks[0].title)
    }

    @Test
    fun deleteTask_removesItem() = runBlocking {
        fakeRepository.addTask("Làm bài tập AI")
        val tasksBefore = fakeRepository.getAllTasks()
        val idToDelete = tasksBefore[0].id

        fakeRepository.deleteTask(idToDelete)
        val tasksAfter = fakeRepository.getAllTasks()
        assertTrue(tasksAfter.isEmpty())
    }

    @Test
    fun toggleDone_changesStatus() = runBlocking {
        fakeRepository.addTask("Xem video demo")
        val task = fakeRepository.getAllTasks()[0]

        fakeRepository.toggleDone(task.id)
        val updatedTask = fakeRepository.getAllTasks()[0]
        assertTrue(updatedTask.isDone)
    }
}
