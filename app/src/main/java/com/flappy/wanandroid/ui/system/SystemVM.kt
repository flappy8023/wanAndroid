package com.flappy.wanandroid.ui.system

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.util.safeApiCall
import com.flappy.wanandroid.vo.TreeItem

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:50 2022/10/17
 */
class SystemVM : BaseViewModel() {
    var cid: Long = 0
    val trees = MutableLiveData<List<TreeItem>>()
    val treeArticles = Pager(
        config = PagingConfig(30, enablePlaceholders = false),
        pagingSourceFactory = { SystemRepository.getArticlesOfTree(cid) }
    ).flow.cachedIn(viewModelScope)

    suspend fun getSystemTrees() {
        val result = safeApiCall {
            SystemRepository.getSystemTree()
        }
        if (result.isSuccess) {
            trees.postValue(result.getOrNull())
        }
    }
}