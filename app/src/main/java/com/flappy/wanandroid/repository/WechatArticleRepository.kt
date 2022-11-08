package com.flappy.wanandroid.repository

import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.paging.WechatArticlePagingSource
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:微信公众号相关仓储类
 * @Date: Created in 22:16 2022/9/9
 */
class WechatArticleRepository {

    suspend fun getWxAccountList() = safeApiCall { ApiManager.service.getWechatAccountList() }

    fun getWxArticleList(id: Long) = WechatArticlePagingSource(id)

}