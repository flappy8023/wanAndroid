package com.flappy.wanandroid.ui.home.square

import com.flappy.wanandroid.base.BasePagingSource
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.model.Article

/**
 * @Author: luweiming
 * @Description:问答分页数据
 * @Date: Created in 23:21 2022/11/2
 */
class SquarePagingSource : BasePagingSource<Article>(){
    override suspend fun doRequest(page: Int) = ApiManager.service.getSquareArticleList(page, PAGE_SIZE)

}