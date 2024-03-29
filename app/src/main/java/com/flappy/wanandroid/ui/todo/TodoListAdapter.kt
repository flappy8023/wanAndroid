package com.flappy.wanandroid.ui.todo

import android.annotation.SuppressLint
import android.graphics.Paint
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

    @SuppressLint("ClickableViewAccessibility")
    inner class TodoViewHolder(val binding: TodoItemListBinding) : ViewHolder(binding.root) {
        init {
            binding.cbStatus.setOnTouchListener { v, event ->
                kotlin.run {
                    if (event.action == KeyEvent.ACTION_UP) {
                        clickListener?.toggleState(
                            getItem(bindingAdapterPosition)!!,
                            bindingAdapterPosition
                        )
                    }
                    true
                }
            }
            binding.root.setOnClickListener {
                clickListener?.showDetail(getItem(bindingAdapterPosition)!!, bindingAdapterPosition)
            }
            binding.tvDelete.setOnClickListener {
                clickListener?.delete(getItem(bindingAdapterPosition)!!, bindingAdapterPosition)
            }
        }

        fun bindView(data: Todo?, position: Int) {
            data?.let { todo ->
                val done = todo.status == 1
                binding.cbStatus.isChecked = done
                binding.tvTitle.text = todo.title
                //已完成设置删除线
                binding.tvTitle.apply {
                    paintFlags = if (done) {
                        paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
                    } else {
                        paintFlags.and(Paint.STRIKE_THRU_TEXT_FLAG.inv())
                    }
                }
                binding.tvDate.text = todo.dateStr
                binding.tvContent.text = todo.content
                binding.root.post {
                    val menuLayoutParam = binding.layoutMenu.layoutParams
                    menuLayoutParam.height = binding.root.measuredHeight
                    binding.layoutMenu.layoutParams = menuLayoutParam
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