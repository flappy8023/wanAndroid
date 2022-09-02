package com.flappy.wanandroid.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.flappy.wanandroid.api.ApiService
import com.flappy.wanandroid.vo.Article
import com.flappy.wanandroid.db.MyDB
import kotlinx.coroutines.flow.Flow

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:22 2022/9/1
 */
class ArticleRepository(val api: ApiService, val db: MyDB) {

    @OptIn(ExperimentalPagingApi::class)
    fun getArticles(pageSize: Int): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(pageSize),
            remoteMediator = ArticlePageMediator(api, db)
        ) { db.articleDao().getArticles() }.flow
}