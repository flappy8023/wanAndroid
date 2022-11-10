package com.flappy.wanandroid.ui.todo

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.repository.TodoRepository

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:21 2022/11/8
 */
class TodoVM : BaseViewModel() {
    companion object {
        private const val PAGE_SIZE = 30
    }

    fun todoList(status: Int? = null, type: Int? = null, priority: Int?=null, orderBy: Int? = 4) = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = {
            TodoRepository.todoListPagingSource(
                status,
                type,
                priority,
                orderBy
            )
        }).flow.cachedIn(viewModelScope)
}