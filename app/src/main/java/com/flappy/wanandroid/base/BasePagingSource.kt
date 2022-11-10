package com.flappy.wanandroid.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flappy.wanandroid.data.api.ApiException
import com.flappy.wanandroid.data.api.ApiResponse
import com.flappy.wanandroid.data.model.PagedData
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:抽离获取分页数据的公共逻辑，子类实现具体的请求函数执行分页请求
 * @Date: Created in 9:30 2022/11/4
 */
abstract class BasePagingSource<T : Any> :
    PagingSource<Int, T>() {
    open var startPage = 0
    companion object {
        const val PAGE_SIZE = 30
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        //第一次加载params.key为空
        val page = params.key ?: startPage
        val result = safeApiCall {
            doRequest(page)
        }
        val nextPage = if (page >= result.getOrNull()!!.pageCount - 1) null else page + 1
        if (result.isSuccess) {
            val list = result.getOrNull()?.datas
            if (null != list) {
                return LoadResult.Page(
                    list, prevKey = when (page) {
                        startPage -> null
                        else -> page - 1
                    }, nextKey = nextPage
                )
            }
            return LoadResult.Error(ApiException(-1, ""))
        } else {
            return LoadResult.Error(result.exceptionOrNull()!!)
        }
    }

    abstract suspend fun doRequest(page: Int): ApiResponse<PagedData<T>>

}