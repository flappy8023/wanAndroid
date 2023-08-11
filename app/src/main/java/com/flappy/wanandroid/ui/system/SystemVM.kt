package com.flappy.wanandroid.ui.system

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.TreeItem
import com.flappy.wanandroid.data.repository.SystemRepository
import com.flappy.wanandroid.util.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:50 2022/10/17
 */
@HiltViewModel
class SystemVM @Inject constructor(val systemRepository: SystemRepository) : BaseViewModel() {

    init {
        launch {
            getSystemTrees()
        }
    }

    val trees = MutableLiveData<List<TreeItem>>()

    private suspend fun getSystemTrees() {
        val result = safeApiCall {
            systemRepository.getSystemTree()
        }
        if (result.isSuccess) {
            trees.postValue(result.getOrNull())
        }
    }

    fun systemArticles(cid: Long) = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = { systemRepository.getArticlesOfTree(cid) }).flow.cachedIn(
        viewModelScope
    )
}