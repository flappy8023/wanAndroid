package com.flappy.wanandroid.ui.system

import com.flappy.wanandroid.base.BasePagingSource
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.model.Article

/**
 * @Author: luweiming
 * @Description:知识体系下子目录文章列表分页数据
 * @Date: Created in 10:00 2022/10/18
 */
class TreeArticlesPagingSource(val apiService: ApiService, private val childId: Long) :
    BasePagingSource<Article>() {
    override suspend fun doRequest(page: Int) = apiService.getArticlesOfTree(
        page, childId, PAGE_SIZE
    )
}