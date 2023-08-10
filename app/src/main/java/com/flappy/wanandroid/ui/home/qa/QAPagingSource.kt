package com.flappy.wanandroid.ui.home.qa

import com.flappy.wanandroid.base.BasePagingSource
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.model.Article

/**
 * @Author: luweiming
 * @Description:问答分页数据
 * @Date: Created in 23:21 2022/11/2
 */
class QAPagingSource(private val apiService: ApiService) : BasePagingSource<Article>() {
    override suspend fun doRequest(page: Int) = apiService.getQAList(page, PAGE_SIZE)
}