package com.example.mob21firabase.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.mob21firabase.data.repo.TodoRepo
import com.example.mob21firabase.data.repo.TodoRepoFirebase
import com.example.mob21firabase.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TodoRepo
): BaseViewModel() {
    fun getAllTask() = repo.getAllTasks()

    init {
        viewModelScope.launch {
            errorHandler {
//                repo.getTasksByTitle("abc")
            }?.let {
                Log.d("debugging", it.toString())
            }
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch{
            errorHandler{repo.deleteTask(taskId)}
//            safeApiCall(func = {repo.deleteTask(taskId)})
        }
    }
}