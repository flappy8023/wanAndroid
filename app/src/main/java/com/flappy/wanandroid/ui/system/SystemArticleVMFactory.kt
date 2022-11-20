package com.flappy.wanandroid.ui.system

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:40 2022/11/20
 */
@Suppress("UNCHECKED_CAST")
class SystemArticleVMFactory(val cid: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == SystemArticleVM::class.java) {
            return SystemArticleVM(cid) as T
        }
        return super.create(modelClass)
    }
}