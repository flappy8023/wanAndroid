package com.flappy.wanandroid.ui.wechat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 23:38 2022/9/9
 */
@Suppress("UNCHECKED_CAST")
class WechatArticleVMFactory(val wechatId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WechatArticleVM::class.java)) {
            return WechatArticleVM(wechatId) as T
        }
        throw IllegalArgumentException("unSupport model class")
    }
}