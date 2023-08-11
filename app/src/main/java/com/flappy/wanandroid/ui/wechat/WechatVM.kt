package com.flappy.wanandroid.ui.wechat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.model.WXOfficialAccount
import com.flappy.wanandroid.data.repository.WechatArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:37 2022/9/9
 */
@HiltViewModel
class WechatVM @Inject constructor(val repository: WechatArticleRepository) : BaseViewModel() {

    val wechatAccounts: MutableLiveData<List<WXOfficialAccount>> by lazy {
        MutableLiveData()
    }

    /**
     * 获取公众号列表
     */
    suspend fun getWechatAccountList() {
        val result = repository.getWxAccountList()
        if (result.isSuccess) {
            wechatAccounts.postValue(result.getOrNull())
        }
    }

    fun wechatArticles(wechatId:Long) =  Pager(config = PagingConfig(30)) {
        repository.getWxArticleList(wechatId)
    }.flow.cachedIn(viewModelScope)


}