package com.example.mob21firabase.ui.addEditTodo.base

import androidx.lifecycle.ViewModel
import com.example.mob21firabase.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseManageViewModel: BaseViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    abstract fun submitTask(title: String, desc: String)
}