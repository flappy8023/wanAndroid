package com.flappy.wanandroid.ui.wechat

import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.data.repository.WechatArticleRepository
import com.flappy.wanandroid.data.model.WXOfficialAccount

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:37 2022/9/9
 */
class WechatVM : BaseViewModel() {

    private val repository = WechatArticleRepository()
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