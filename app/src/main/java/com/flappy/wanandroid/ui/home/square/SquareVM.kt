package com.flappy.wanandroid.ui.home.square

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.repository.ArticleRepository
import com.flappy.wanandroid.ui.home.content.HomeContentVM

/**
 * @Author: luweiming
 * @Description:广场页面ViewModel
 * @Date: Created in 23:05 2022/11/2
 */
class SquareVM : BaseViewModel() {
    fun squareList() = Pager(
        PagingConfig(HomeContentVM.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { ArticleRepository(ApiManager.service).squarePagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}