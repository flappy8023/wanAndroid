package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.ui.mine.collection.CollectedArticlePagingSource
import com.flappy.wanandroid.util.safeApiCall
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:40 2022/12/7
 */
class CollectionRepository @Inject constructor(private val apiService: ApiService) {
    /**
     * 获取收藏的文章列表分页数据
     *
     * @return
     */
    fun getCollectedArticlesPagingSource() = CollectedArticlePagingSource(apiService)

    /**
     * 获取收藏的网址
     *
     * @return
     */
    suspend fun getCollectedWebsites() =
        safeApiCall { apiService.getCollectedWebsiteList() }
}