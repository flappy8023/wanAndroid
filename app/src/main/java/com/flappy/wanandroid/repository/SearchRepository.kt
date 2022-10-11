package com.flappy.wanandroid.repository

import com.flappy.wanandroid.api.ApiManager
import com.flappy.wanandroid.paging.ArticlePagingSource
import com.flappy.wanandroid.paging.SearchResultPagingSource
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:14 2022/9/20
 */
class SearchRepository {
    suspend fun requestHotWords() = safeApiCall {
        ApiManager.service.getHotKey()
    }

    fun searchArticlePagingSource(keyWords:String) = SearchResultPagingSource(keyWords)

}