package com.flappy.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.model.BannerItem
import com.flappy.wanandroid.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:16 2022/11/2
 */
@HiltViewModel
class HomeVM @Inject constructor(private val repository: HomeRepository) : BaseViewModel() {
    companion object {
        const val PAGE_SIZE = 30
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

    val qaList = Pager(
        PagingConfig(PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { repository.qaPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)

    val squareList = Pager(
        PagingConfig(PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { repository.squarePagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}