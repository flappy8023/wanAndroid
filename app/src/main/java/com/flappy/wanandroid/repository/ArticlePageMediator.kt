package com.flappy.wanandroid.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.flappy.wanandroid.api.ApiService
import com.flappy.wanandroid.db.MyDB
import com.flappy.wanandroid.db.dao.ArticleDao
import com.flappy.wanandroid.db.dao.ArticleKeyDao
import com.flappy.wanandroid.vo.Article
import com.flappy.wanandroid.vo.ArticleKey

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:56 2022/9/1
 */
@ExperimentalPagingApi
class ArticlePageMediator(private val api: ApiService, private val db: MyDB) :
    RemoteMediator<Int, Article>() {
    private val keyDao: ArticleKeyDao = db.articleKeyDao()
    private val articleDao: ArticleDao = db.articleDao()
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        val page = when (loadType) {
            //
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKey = db.withTransaction { keyDao.getByCategory("home") }
                if (remoteKey.nextKey.isNullOrEmpty() ) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                remoteKey.nextKey!!.toInt()
            }

        }
        //刷新时默认请求三页
        val list = api.getHomeArticleList(
            page!!, when (loadType) {
                LoadType.REFRESH -> state.config.initialLoadSize
                else -> state.config.pageSize
            }
        ).data.datas
        db.withTransaction {
            if (loadType == LoadType.REFRESH) {
                articleDao.deleteAll()
                keyDao.deleteByCategory("home")
            }
            keyDao.insert(ArticleKey("home", (page + 1).toString()))
            articleDao.insertAll(list)
        }
        return MediatorResult.Success(endOfPaginationReached = list.isEmpty())


    }
}
