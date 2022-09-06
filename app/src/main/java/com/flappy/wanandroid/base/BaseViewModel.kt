package com.flappy.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:50 2022/8/21
 */
open class BaseViewModel:ViewModel() {

    open fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }
}