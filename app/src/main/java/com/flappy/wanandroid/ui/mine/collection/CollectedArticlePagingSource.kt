package com.flappy.wanandroid.ui.mine.collection

import com.flappy.wanandroid.Const
import com.flappy.wanandroid.base.BasePagingSource
import com.flappy.wanandroid.data.api.ApiResponse
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.PagedData

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 16:42 2022/12/7
 */
class CollectedArticlePagingSource(val apiService: ApiService) : BasePagingSource<Article>() {
    override suspend fun doRequest(page: Int): ApiResponse<PagedData<Article>> {
        return apiService.getCollectList(page, Const.PAGE_SIZE)
    }
}