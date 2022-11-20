package com.flappy.wanandroid.ui.system

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:42 2022/11/20
 */
class SystemArticleVM(cid: Long) : BaseViewModel() {
    val systemArticles = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = { SystemRepository.getArticlesOfTree(cid) }).flow.cachedIn(
        viewModelScope
    )
}