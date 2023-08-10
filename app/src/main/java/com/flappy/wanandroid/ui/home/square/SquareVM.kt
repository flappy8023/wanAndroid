package com.flappy.wanandroid.ui.home.square

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.repository.HomeRepository
import com.flappy.wanandroid.ui.home.discovery.DiscoveryVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:广场页面ViewModel
 * @Date: Created in 23:05 2022/11/2
 */
@HiltViewModel
class SquareVM @Inject constructor(homeRepository: HomeRepository) : BaseViewModel() {
    val squareList = Pager(
        PagingConfig(DiscoveryVM.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { homeRepository.squarePagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}