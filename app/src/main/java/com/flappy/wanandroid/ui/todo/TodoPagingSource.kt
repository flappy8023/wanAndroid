package com.flappy.wanandroid.ui.todo

import com.flappy.wanandroid.base.BasePagingSource
import com.flappy.wanandroid.data.api.ApiResponse
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.model.PagedData
import com.flappy.wanandroid.data.model.Todo

/**
 * @Author: luweiming
 * @Description:待办清单分页请求数据
 * @Date: Created in 10:51 2022/11/10
 */
class TodoPagingSource(private val apiService: ApiService, val status: Int?, val type: Int?) :
    BasePagingSource<Todo>() {
    override var startPage = 1
    override suspend fun doRequest(page: Int): ApiResponse<PagedData<Todo>> =
        apiService.getTODOList(page, status, type)

}