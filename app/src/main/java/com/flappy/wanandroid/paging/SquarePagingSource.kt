package com.flappy.wanandroid.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flappy.wanandroid.data.api.ApiException
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.api.ApiResponse
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.PagedData
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:问答分页数据
 * @Date: Created in 23:21 2022/11/2
 */
class SquarePagingSource : BasePagingSource<Article>(){
    override suspend fun doRequest(page: Int) = ApiManager.service.getSquareArticleList(page, PAGE_SIZE)

}