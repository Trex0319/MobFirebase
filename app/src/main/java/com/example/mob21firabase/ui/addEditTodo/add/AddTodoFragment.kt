package com.example.mob21firabase.ui.addEditTodo.add

import androidx.fragment.app.viewModels
import com.example.mob21firabase.ui.addEditTodo.base.BaseManageFragment
import com.example.mob21firebase.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment: BaseManageFragment() {

    override val viewModel: AddTodoViewModel by viewModels()

}