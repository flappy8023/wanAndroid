package com.flappy.wanandroid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flappy.wanandroid.api.ApiException
import com.flappy.wanandroid.api.ApiService
import com.flappy.wanandroid.util.safeApiCall
import com.flappy.wanandroid.vo.Article

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:11 2022/9/9
 */
class WxArticlePagingSource(val api: ApiService, val id: Long) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 0
        val result = safeApiCall { api.getWxHistoryArticles(id, page) }
        if (result.isSuccess) {
            val list = result.getOrNull()?.datas
            if (null != list) {
                return LoadResult.Page(
                    list, prevKey = when (page) {
                        ArticlePagingSource.STARTING_KEY -> null
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