package com.flappy.wanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flappy.wanandroid.data.api.ApiException
import com.flappy.wanandroid.data.api.ApiResponse
import com.flappy.wanandroid.data.api.ApiResult
import com.flappy.wanandroid.data.api.ExceptionHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:50 2022/8/21
 */
open class BaseViewModel : ViewModel() {

    open fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }


    suspend fun <T : Any> safeCall(call: suspend () -> ApiResponse<T>): ApiResult<T?> {
        return try {
            val response = call.invoke()
            if (response.isSuccess) {
                ApiResult.Success(response.data)
            } else {
                ApiResult.Failure(ApiException(response.errorCode, response.errorMsg))
            }
        } catch (e: Exception) {
            ApiResult.Failure(ExceptionHandle.handleException(e))
        }
    }


}