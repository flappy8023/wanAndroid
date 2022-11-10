package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.ui.todo.TodoPagingSource

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:27 2022/11/10
 */
object TodoRepository {
    fun todoListPagingSource(status: Int?, type: Int?, priority: Int?, orderBy: Int?) =
        TodoPagingSource(status, type, priority, orderBy)
}