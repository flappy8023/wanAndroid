package com.flappy.wanandroid.ui.home.qa

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
 * @Description:
 * @Date: Created in 23:04 2022/11/2
 */
class HomeQAVM : BaseViewModel() {
    fun getQAList() = Pager(
        PagingConfig(HomeContentVM.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { ArticleRepository(ApiManager.service).qaPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}