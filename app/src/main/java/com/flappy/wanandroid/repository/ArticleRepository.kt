package com.flappy.wanandroid.repository

import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.data.db.MyDB
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.BannerItem
import com.flappy.wanandroid.paging.ArticlePagingSource
import com.flappy.wanandroid.paging.QAPagingSource
import com.flappy.wanandroid.paging.SquarePagingSource
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:22 2022/9/1
 */
class ArticleRepository(val api: ApiService, val db: MyDB? = null) {

    fun articlePagingSource() = ArticlePagingSource(api)

    /**
     * 获取置顶内容
     * @return Result<List<Article>>
     */
    suspend fun getTops(): Result<List<Article>> {
        return safeApiCall { api.getTopArticles() }
    }

    /**
     * 获取轮播内容
     * @return Result<List<BannerItem>>
     */
    suspend fun getBanners(): Result<List<BannerItem>> {
        return safeApiCall { api.getBanners() }
    }

    /**
     * 获取问答列表
     */
    fun qaPagingSource() = QAPagingSource()

    /**
     * 广场分页数据
     */
    fun squarePagingSource() = SquarePagingSource()
}