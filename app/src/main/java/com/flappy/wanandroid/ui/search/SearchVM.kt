package com.flappy.wanandroid.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.Article
import com.flappy.wanandroid.data.repository.SearchRepository
import com.flappy.wanandroid.ui.home.discovery.DiscoveryVM
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:41 2022/9/19
 */
class SearchVM : BaseViewModel() {
    init {
        requestHotWords()
    }

    /**
     * 热门搜索
     */
    val hotWords: MutableLiveData<List<String>?> = MutableLiveData()


    /**
     * 搜索结果
     */
    fun searchArticles(keyWord: String): Flow<PagingData<Article>> =
        Pager(
            PagingConfig(DiscoveryVM.PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { SearchRepository.searchArticlePagingSource(keyWord) }
        ).flow.cachedIn(viewModelScope)


    fun requestHotWords() {
        viewModelScope.launch {
            val result = SearchRepository.requestHotWords()
            if (result.isSuccess) {
                val list = result.getOrNull()?.map { it.name }
                hotWords.postValue(list)
            }
        }
    }

}