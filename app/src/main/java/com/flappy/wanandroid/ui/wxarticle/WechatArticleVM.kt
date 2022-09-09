package com.flappy.wanandroid.ui.wxarticle

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.repository.WxArticleRepository
import com.flappy.wanandroid.vo.Article
import kotlinx.coroutines.flow.Flow

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:22 2022/9/9
 */
class WechatArticleVM(val wechatId: Long) : BaseViewModel() {
    companion object {
        const val PAGE_SIZE = 30
    }

    private val repository: WxArticleRepository by lazy { WxArticleRepository() }
    val wechatArticles: Flow<PagingData<Article>> = Pager(config = PagingConfig(PAGE_SIZE)) {
        repository.getWxArticleList(wechatId)
    }.flow.cachedIn(viewModelScope)
}