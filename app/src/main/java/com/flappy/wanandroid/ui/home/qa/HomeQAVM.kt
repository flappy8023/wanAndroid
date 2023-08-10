package com.flappy.wanandroid.ui.home.qa

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
 * @Description:
 * @Date: Created in 23:04 2022/11/2
 */
@HiltViewModel
class HomeQAVM @Inject constructor(private val homeRepository: HomeRepository) : BaseViewModel() {
    val qaList = Pager(
        PagingConfig(DiscoveryVM.PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { homeRepository.qaPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)
}