package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.ui.system.TreeArticlesPagingSource
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:42 2022/10/18
 */
class SystemRepository @Inject constructor(val apiService: ApiService) {
    suspend fun getSystemTree() = apiService.getTrees()

    fun getArticlesOfTree(cid: Long) = TreeArticlesPagingSource(apiService, cid)
}