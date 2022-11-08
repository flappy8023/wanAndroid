package com.flappy.wanandroid.paging

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.model.Article

/**
 * @Author: luweiming
 * @Description:搜索结果分页数据
 * @Date: Created in 13:13 2022/9/6
 */
class SearchResultPagingSource(val keyWord: String) : BasePagingSource<Article>() {
    override suspend fun doRequest(page: Int) =
        ApiManager.service.searchArticle(page, PAGE_SIZE, keyWord)
}