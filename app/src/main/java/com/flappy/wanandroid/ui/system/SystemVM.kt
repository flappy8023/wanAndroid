package com.flappy.wanandroid.ui.system

import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.TreeItem
import com.flappy.wanandroid.util.safeApiCall

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:50 2022/10/17
 */
class SystemVM : BaseViewModel() {
    val trees = MutableLiveData<List<TreeItem>>()

    suspend fun getSystemTrees() {
        val result = safeApiCall {
            SystemRepository.getSystemTree()
        }
        if (result.isSuccess) {
            trees.postValue(result.getOrNull())
        }
    }
}