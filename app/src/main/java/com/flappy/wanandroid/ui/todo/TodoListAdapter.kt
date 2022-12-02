package com.flappy.wanandroid.ui.todo

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.flappy.wanandroid.data.model.Todo
import com.flappy.wanandroid.databinding.TodoItemListBinding

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:47 2022/11/10
 */
class TodoListAdapter :
    PagingDataAdapter<Todo, TodoListAdapter.TodoViewHolder>(diffCallback = object :
        DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = oldItem.id == newItem.id
    }) {
    var clickListener: ItemClickListener? = null

    interface ItemClickListener {
        fun toggleState(todo: Todo, position: Int)
        fun showDetail(todo: Todo, position: Int)
        fun delete(todo: Todo, position: Int)
    }

    inner class TodoViewHolder(val binding: TodoItemListBinding) : ViewHolder(binding.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun bindView(data: Todo?, position: Int) {
            data?.let { todo ->
                binding.cbStatus.isChecked = todo.status == 1
                binding.tvTitle.text = todo.title
                binding.tvDate.text = todo.dateStr
                binding.tvContent.text = todo.content
                binding.cbStatus.setOnTouchListener { v, event ->
                    kotlin.run {
                        if (event.action == KeyEvent.ACTION_UP) {
                            clickListener?.toggleState(todo, bindingAdapterPosition)
                        }
                        true
                    }
                }
                binding.root.setOnClickListener {
                    clickListener?.showDetail(todo, bindingAdapterPosition)
                }
            }

        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindView(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            TodoItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}