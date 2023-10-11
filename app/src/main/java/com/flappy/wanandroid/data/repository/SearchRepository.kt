package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.base.BasePagingSource
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.util.safeApiCall
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:14 2022/9/20
 */
class SearchRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun requestHotWords() = safeApiCall {
        apiService.getHotKey()
    }

    fun searchArticlePagingSource(keyWords: String) = BasePagingSource {
        apiService.searchArticle(it, keyWords)
    }

}