package com.flappy.wanandroid.ui.system

import com.flappy.wanandroid.api.ApiManager
import com.flappy.wanandroid.paging.TreeArticlesPagingSource

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:42 2022/10/18
 */
object SystemRepository {
    suspend fun getSystemTree() = ApiManager.service.getTrees()

    fun getArticlesOfTree(cid:Long) = TreeArticlesPagingSource(cid)
}