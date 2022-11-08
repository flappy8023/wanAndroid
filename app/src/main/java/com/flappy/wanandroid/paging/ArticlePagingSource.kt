package com.flappy.wanandroid.paging

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.model.Article

/**
 * @Author: luweiming
 * @Description: 文章列表分页请求
 * @Date: Created in 13:13 2022/9/6
 */
class ArticlePagingSource : BasePagingSource<Article>() {
    override suspend fun doRequest(page: Int) = ApiManager.service.getHomeArticleList(page, PAGE_SIZE)
}