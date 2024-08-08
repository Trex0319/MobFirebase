package com.example.mob21firabase.ui.home

import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mob21firabase.data.model.Task
import com.example.mob21firabase.ui.adapter.TaskAdapter
import com.example.mob21firabase.ui.base.BaseFragment
import com.example.mob21firebase.R
import com.example.mob21firebase.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_home

    private lateinit var adapter: TaskAdapter

    override fun onBindView(view: View) {
        super.onBindView(view)

        setupAdapter()

        binding?.fabAdd?.setOnClickListener{
            findNavController(this@HomeFragment).navigate(
                HomeFragmentDirections.actionHomeFragmentToAddTodoFragment()
            )
        }
    }

    override fun onBindData(view: View) {
        super.onBindData(view)
        lifecycleScope.launch {
            viewModel.getAllTask().collect {
                adapter.setTasks(it)
                binding?.tvContent?.isInvisible = adapter.itemCount !=0
            }
        }
    }

    private fun setupAdapter() {
        adapter = TaskAdapter(emptyList())
        binding?.rvTodos?.adapter = adapter
        binding?.rvTodos?.layoutManager = LinearLayoutManager(requireContext())
        adapter.listener = object: TaskAdapter.Listener {
            override fun onClickItem(todo: Task) {
                findNavController(this@HomeFragment).navigate(
                    HomeFragmentDirections.actionHomeFragmentToEditTodoFragment(todo.id!!)
                )
            }

            override fun onDeleteItem(task: Task) {
                viewModel.deleteTask(task.id!!
                )
            }

            override fun onLongClick(task: Task) {
                TODO("Not yet implemented")
            }
        }
    }
}