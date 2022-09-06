package com.flappy.wanandroid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flappy.wanandroid.api.ApiException
import com.flappy.wanandroid.api.ApiService
import com.flappy.wanandroid.util.apiCall
import com.flappy.wanandroid.vo.Article

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 13:13 2022/9/6
 */
class ArticlePagingSource(val api: ApiService) : PagingSource<Int, Article>() {
    companion object {
        const val STARTING_KEY = 0
        const val PAGE_SIZE = 30
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        //第一次加载params.key为空
        val page = params.key ?: STARTING_KEY
        val result = apiCall { api.getHomeArticleList(page, PAGE_SIZE) }
        if (result.isSuccess) {
            val list = result.getOrNull()?.datas
            if (null != list) {
                return LoadResult.Page(
                    list, prevKey = when (page) {
                        STARTING_KEY -> null
                        else -> page - 1
                    }, nextKey = page + 1
                )
            }
            return LoadResult.Error(ApiException(-1, ""))
        } else {
            return LoadResult.Error(result.exceptionOrNull()!!)
        }
    }
}