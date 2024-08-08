package com.example.mob21firabase.ui.addEditTodo.base

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.mob21firabase.ui.base.BaseFragment
import com.example.mob21firabase.ui.base.BaseViewModel
import com.example.mob21firebase.R
import com.example.mob21firebase.databinding.FragmentManageTodoBinding
import kotlinx.coroutines.launch

abstract class BaseManageFragment: BaseFragment<FragmentManageTodoBinding>() {

    override val viewModel: BaseManageViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_manage_todo

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.finish.collect {
                findNavController().popBackStack()
            }
        }
    }

    override fun onBindView(view: View) {
        super.onBindView(view)
        binding?.btnSubmit?.setOnClickListener {
            val title = binding?.etTitle?.text.toString()
            val desc = binding?.etDesc?.text.toString()

            viewModel.submitTask(title, desc)
        }
    }

}