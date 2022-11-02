package com.flappy.wanandroid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flappy.wanandroid.data.api.ApiException
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:问答分页数据
 * @Date: Created in 23:21 2022/11/2
 */
class SquarePagingSource : PagingSource<Int, Article>() {
    companion object {
        const val STARTING_KEY = 0
        const val PAGE_SIZE = 30
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Article> {
        //第一次加载params.key为空
        val page = params.key ?: STARTING_KEY
        val result = safeApiCall { ApiManager.service.getSquareArticleList(page, PAGE_SIZE) }
        val nextPage = if (page == result.getOrNull()!!.pageCount - 1) null else page + 1
        if (result.isSuccess) {
            val list = result.getOrNull()?.datas
            if (null != list) {
                return PagingSource.LoadResult.Page(
                    list, prevKey = when (page) {
                        STARTING_KEY -> null
                        else -> page - 1
                    }, nextKey = nextPage
                )
            }
            return PagingSource.LoadResult.Error(ApiException(-1, ""))
        } else {
            return PagingSource.LoadResult.Error(result.exceptionOrNull()!!)
        }
    }
}