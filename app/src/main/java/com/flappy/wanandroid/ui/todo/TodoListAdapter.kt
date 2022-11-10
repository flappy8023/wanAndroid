package com.flappy.wanandroid.ui.todo

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
    class TodoViewHolder(val binding: TodoItemListBinding) : ViewHolder(binding.root) {
        fun bindView(data: Todo?, position: Int) {
            binding.cbStatus.isChecked = data?.status == 1
            binding.tvTitle.text = data?.title
            binding.tvDate.text = data?.dateStr
            binding.tvType.text = "类型"
            binding.tvContent.text = data?.content
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindView(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}