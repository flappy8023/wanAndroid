package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.ui.search.SearchResultPagingSource
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:14 2022/9/20
 */
object SearchRepository {
    suspend fun requestHotWords() = safeApiCall {
        ApiManager.service.getHotKey()
    }

    fun searchArticlePagingSource(keyWords:String) = SearchResultPagingSource(keyWords)

}