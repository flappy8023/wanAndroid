package com.flappy.wanandroid.ui.home.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.api.ApiService
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.db.MyDB
import com.flappy.wanandroid.repository.ArticleRepository
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.BannerItem
import kotlinx.coroutines.flow.Flow

/**
 * @Author: luweiming
 * @Description:首页对应viewmodel
 * @Date: Created in 17:07 2022/8/30
 */
class HomeContentVM : BaseViewModel() {
    companion object {
        const val PAGE_SIZE = 30
    }

    private val db: MyDB by lazy { MyDB.buildDataBase(MyApp.app) }
    private val api: ApiService by lazy { ApiManager.service }
    private val repository: ArticleRepository by lazy {
        ArticleRepository(api, db)
    }

    /**
     * 轮播图文数据
     */
    val banners: MutableLiveData<List<BannerItem>> = MutableLiveData()

    /**
     * 置顶内容
     */
    val tops: MutableLiveData<List<Article>> = MutableLiveData()


    /**
     * 文章列表
     */
    val articles: Flow<PagingData<Article>> = Pager(
        PagingConfig(PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { repository.articlePagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)

    fun getTops() {
        launch {
            val result = repository.getTops()
            if (result.isSuccess) {
                tops.postValue(result.getOrNull())
            }
        }
    }

    fun getBanners() {
        launch {
            val result = repository.getBanners()
            if (result.isSuccess) {
                banners.postValue(result.getOrNull())
            }
        }
    }

}