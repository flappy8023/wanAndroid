package com.flappy.wanandroid.paging

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.model.Article

/**
 * @Author: luweiming
 * @Description:微信公帐号文章列表分页数据
 * @Date: Created in 23:11 2022/9/9
 */
class WechatArticlePagingSource(val id: Long) : BasePagingSource<Article>() {
    override suspend fun doRequest(page: Int) =
        ApiManager.service.getWechatHistoryArticles(id, page, PAGE_SIZE)
}