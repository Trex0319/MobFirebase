package com.example.mob21firabase.ui.addEditTodo.add

import androidx.lifecycle.viewModelScope
import com.example.mob21firabase.data.model.Extra
import com.example.mob21firabase.data.model.Task
import com.example.mob21firabase.data.repo.TodoRepo
import com.example.mob21firabase.data.repo.TodoRepoFirebase
import com.example.mob21firabase.ui.addEditTodo.base.BaseManageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repo: TodoRepo
) : BaseManageViewModel() {

    override fun submitTask(title: String, desc: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTask(Task(
                title = title,
                desc = desc,
                extra = Extra(location = "Singapore", time = "Today")
            )
            )
            finish.emit(Unit)
        }
    }
}