package com.example.mob21firabase.data.repo

import com.example.mob21firabase.data.model.Task
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.lang.Exception

interface TodoRepo {

    fun getAllTasks(): Flow<List<Task>>

    suspend fun addTask(task: Task) : String?

    suspend fun deleteTask(id: String)

    suspend fun updateTask(task: Task)

    suspend fun getTaskById(id: String): Task?
}