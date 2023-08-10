package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.ui.wechat.WechatArticlePagingSource
import com.flappy.wanandroid.util.safeApiCall
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:微信公众号相关仓储类
 * @Date: Created in 22:16 2022/9/9
 */
class WechatArticleRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getWxAccountList() = safeApiCall { apiService.getWechatAccountList() }

    fun getWxArticleList(id: Long) = WechatArticlePagingSource(apiService, id)

}