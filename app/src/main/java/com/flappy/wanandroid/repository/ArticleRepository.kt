package com.flappy.wanandroid.repository

import com.flappy.wanandroid.api.ApiService
import com.flappy.wanandroid.db.MyDB
import com.flappy.wanandroid.paging.ArticlePagingSource
import com.flappy.wanandroid.util.safeApiCall
import com.flappy.wanandroid.vo.Article
import com.flappy.wanandroid.vo.BannerItem

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:22 2022/9/1
 */
class ArticleRepository(val api: ApiService, val db: MyDB) {

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
}