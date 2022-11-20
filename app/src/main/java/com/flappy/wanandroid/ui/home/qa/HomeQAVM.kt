package com.flappy.wanandroid.ui.home.qa

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.api.ApiManager
import com.flappy.wanandroid.data.repository.HomeRepository
import com.flappy.wanandroid.ui.home.discovery.DiscoveryVM

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:04 2022/11/2
 */
class HomeQAVM : BaseViewModel() {
    val qaList = Pager(
        PagingConfig(DiscoveryVM.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { HomeRepository(ApiManager.service).qaPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}