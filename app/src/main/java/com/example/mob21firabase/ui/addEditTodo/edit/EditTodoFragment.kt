package com.example.mob21firabase.ui.addEditTodo.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mob21firabase.ui.addEditTodo.base.BaseManageFragment
import com.example.mob21firebase.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditTodoFragment: BaseManageFragment() {
    override val viewModel: EditTodoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnSubmit?.text = getString(R.string.update)

        lifecycleScope.launch {
            viewModel.task.collect {
                if (it!= null) {
                    binding?.etTitle?.setText(it.title)
                    binding?.etDesc?.setText(it.desc)
                }
            }
        }
    }
}