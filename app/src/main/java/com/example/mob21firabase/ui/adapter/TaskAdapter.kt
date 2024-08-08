package com.example.mob21firabase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mob21firabase.data.model.Task
import com.example.mob21firebase.databinding.ItemtodoBinding

class TaskAdapter(
    private var todos: List<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var listener: Listener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemtodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setTasks(todos: List<Task>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = todos[position]
        holder.bind(item)
    }


    inner class TaskViewHolder(
        private val binding: ItemtodoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Task) {
            binding.cvTitle.text = todo.title
            binding.cvDesc.text = todo.desc
            binding.ivDelete.setOnClickListener {
                listener?.onDeleteItem(todo)
            }

            binding.cvTask.setOnClickListener{
                listener?.onClickItem(todo)
            }
        }
    }

    interface Listener {
        fun onClickItem(todo: Task)
        fun onDeleteItem(task: Task)
        fun onLongClick(task: Task)
    }
}