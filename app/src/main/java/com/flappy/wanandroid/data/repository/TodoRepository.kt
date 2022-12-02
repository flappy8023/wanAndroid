package com.flappy.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.flappy.wanandroid.Const
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.ui.todo.TodoPagingSource
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:27 2022/11/10
 */
object TodoRepository {
    fun todoListPager(status: Int?, type: Int?) =
        Pager(
            config = PagingConfig(Const.PAGE_SIZE),
            pagingSourceFactory = {
                TodoPagingSource(
                    status,
                    type
                )
            })

    suspend fun addTodo(title: String, content: String, date: String?, type: Int, priority: Int) =
        safeApiCall { ApiManager.service.addTODO(title, content, date, type, priority) }

    suspend fun updateTodoStatus(id: Long, status: Int) =
        safeApiCall { ApiManager.service.updateTODOStatus(id, status) }
}