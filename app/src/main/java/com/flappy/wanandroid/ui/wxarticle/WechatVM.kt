package com.flappy.wanandroid.ui.wxarticle

import androidx.lifecycle.MutableLiveData
import com.flappy.wanandroid.base.BaseViewModel
import com.flappy.wanandroid.repository.WxArticleRepository
import com.flappy.wanandroid.vo.WXOfficialAccount

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 22:37 2022/9/9
 */
class WechatVM : BaseViewModel() {

    private val repository = WxArticleRepository()
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