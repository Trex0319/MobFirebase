package com.example.mob21firabase.data.repo

import com.example.mob21firabase.data.model.Task
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FakeTodoRepoImpl: TodoRepo {

    override fun getAllTasks() = flow<List<Task>> {
        emit(emptyList())
    }

    override suspend fun addTask(task: Task): String? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(id: String): Task? {
        TODO("Not yet implemented")
    }

}