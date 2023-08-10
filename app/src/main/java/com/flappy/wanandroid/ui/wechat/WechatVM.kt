package com.flappy.wanandroid.ui.wechat

import androidx.lifecycle.MutableLiveData
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


}