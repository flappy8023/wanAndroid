package com.flappy.wanandroid.util

import com.flappy.wanandroid.api.ApiException
import com.flappy.wanandroid.api.Response

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:58 2022/9/6
 */
suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
    return Result.runCatching {
        val response = call.invoke()
        if (response.isSuccess || null != response.data) {
            return success(response.data!!)
        }
        return failure(ApiException(response.errorCode, response.errorMsg))
    }
}