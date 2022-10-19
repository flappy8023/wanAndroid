package com.flappy.wanandroid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flappy.wanandroid.api.ApiException
import com.flappy.wanandroid.api.ApiManager
import com.flappy.wanandroid.util.safeApiCall
import com.flappy.wanandroid.vo.Article

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:00 2022/10/18
 */
class TreeArticlesPagingSource(val childId: Long) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 0
        val result = safeApiCall { ApiManager.service.getArticlesOfTree(page, childId, 30) }
        if (result.isSuccess) {
            val list = result.getOrNull()?.datas
            //当前已是最后一页时，不再加载下一页
            val nextPage = if (page == result.getOrNull()!!.pageCount - 1) null else page + 1
            if (null != list) {
                return LoadResult.Page(
                    list, prevKey = when (page) {
                        ArticlePagingSource.STARTING_KEY -> null
                        else -> page - 1
                    }, nextKey = nextPage
                )
            }
            return LoadResult.Error(ApiException(-1, ""))
        } else {
            return LoadResult.Error(result.exceptionOrNull()!!)
        }
    }
}