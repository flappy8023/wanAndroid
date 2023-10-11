package com.flappy.wanandroid.data.repository

import com.flappy.wanandroid.base.BasePagingSource
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.BannerItem
import com.flappy.wanandroid.util.safeApiCall
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:22 2022/9/1
 */
class HomeRepository @Inject constructor(val api: ApiService) {

    fun articlePagingSource() = BasePagingSource {
        api.getHomeArticleList(it)
    }

    /**
     * 获取置顶内容
     * @return Result<List<Article>>
     */
    suspend fun getTops(): Result<List<Article>?> {
        return safeApiCall { api.getTopArticles() }
    }

    /**
     * 获取轮播内容
     * @return Result<List<BannerItem>>
     */
    suspend fun getBanners(): Result<List<BannerItem>?> {
        return safeApiCall { api.getBanners() }
    }

    /**
     * 获取问答列表
     */
    fun qaPagingSource() = BasePagingSource {
        api.getQAList(it)
    }

    /**
     * 广场分页数据
     */
    fun squarePagingSource() = BasePagingSource {
        api.getSquareArticleList(it)
    }
}