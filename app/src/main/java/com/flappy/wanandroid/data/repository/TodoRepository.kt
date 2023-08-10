package com.flappy.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.flappy.wanandroid.Const
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.model.Todo
import com.flappy.wanandroid.ui.todo.TodoPagingSource
import com.flappy.wanandroid.util.safeApiCall
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:27 2022/11/10
 */
class TodoRepository @Inject constructor(private val apiService: ApiService) {
    fun todoListPager(status: Int?, type: Int?) =
        Pager(
            config = PagingConfig(Const.PAGE_SIZE),
            pagingSourceFactory = {
                TodoPagingSource(
                    apiService,
                    status,
                    type
                )
            })

    suspend fun addTodo(title: String, content: String, date: String?, type: Int, priority: Int) =
        safeApiCall { apiService.addTODO(title, content, date, type, priority) }

    suspend fun updateTodoStatus(id: Long, status: Int) =
        safeApiCall { apiService.updateTODOStatus(id, status) }

    suspend fun updateTodo(todo: Todo) = safeApiCall {
        apiService.updateTODO(
            todo.id,
            todo.title,
            todo.content,
            todo.dateStr,
            todo.status,
            todo.type,
            todo.priority
        )
    }

    suspend fun deleteTodo(id: Long) = safeApiCall { apiService.deleteTODO(id) }
}