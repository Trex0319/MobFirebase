package com.example.mob21firabase.ui.addEditTodo.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.mob21firabase.data.model.Task
import com.example.mob21firabase.data.repo.TodoRepo
import com.example.mob21firabase.data.repo.TodoRepoFirebase
import com.example.mob21firabase.ui.addEditTodo.base.BaseManageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTodoViewModel @Inject constructor(
    private val repo: TodoRepo,
    state: SavedStateHandle

): BaseManageViewModel(){
    val task: MutableStateFlow<Task?> = MutableStateFlow(null)
    private val taskId = state.get<String>("taskId")

    init {
        viewModelScope.launch {
            taskId?.let { id ->
                task.value = repo.getTaskById(id)
            }
        }
    }

    override fun submitTask(title: String, desc: String) {
        task.value?.let {
            val newTask = it.copy(title = title, desc = desc)
            viewModelScope.launch {
                repo.updateTask(newTask)
                finish.emit(Unit)
            }
        }
    }
}