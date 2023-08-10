package com.flappy.wanandroid.ui.wechat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.repository.WechatArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:22 2022/9/9
 */
@HiltViewModel
class WechatArticleVM @Inject constructor(
    val repository: WechatArticleRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    companion object {
        const val PAGE_SIZE = 30
    }

    private val wechatId = savedStateHandle.get<Long>("wechatId") ?: 0

    val wechatArticles: Flow<PagingData<Article>> = Pager(config = PagingConfig(PAGE_SIZE)) {
        repository.getWxArticleList(wechatId)
    }.flow.cachedIn(viewModelScope)
}