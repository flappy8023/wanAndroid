package com.flappy.wanandroid.ui.mine.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.Const
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.WebSiteBean
import com.flappy.wanandroid.data.repository.CollectionRepository

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 15:27 2022/12/7
 */
class CollectionVM : BaseViewModel() {
    init {
        getCollectedWebsites()
    }

    private val _websites: MutableLiveData<List<WebSiteBean>> = MutableLiveData()
    val websites: LiveData<List<WebSiteBean>>
        get() = _websites

    fun getCollectedArticles() = Pager(
        config = PagingConfig(Const.PAGE_SIZE),
        pagingSourceFactory = { CollectionRepository.getCollectedArticlesPagingSource() }
    ).flow.cachedIn(viewModelScope)

    fun getCollectedWebsites() {
        launch {
            val result = CollectionRepository.getCollectedWebsites()
            if (result.isSuccess) {
                _websites.value = result.getOrNull()
            }
        }
    }
}