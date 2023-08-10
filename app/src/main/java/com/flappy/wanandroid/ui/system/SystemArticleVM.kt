package com.flappy.wanandroid.ui.system

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.repository.SystemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:42 2022/11/20
 */
@HiltViewModel
class SystemArticleVM @Inject constructor(
    private val systemRepository: SystemRepository,
    private val savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {
    private val cid = savedStateHandle.get<Long>("cid") ?: -1
    val systemArticles = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = { systemRepository.getArticlesOfTree(cid) }).flow.cachedIn(
        viewModelScope
    )
}