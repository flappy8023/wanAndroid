package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.ui.mine.collection.CollectedArticlePagingSource
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:40 2022/12/7
 */
object CollectionRepository {
    /**
     * 获取收藏的文章列表分页数据
     *
     * @return
     */
    fun getCollectedArticlesPagingSource() = CollectedArticlePagingSource()

    /**
     * 获取收藏的网址
     *
     * @return
     */
    suspend fun getCollectedWebsites() =
        safeApiCall { ApiManager.service.getCollectedWebsiteList() }
}