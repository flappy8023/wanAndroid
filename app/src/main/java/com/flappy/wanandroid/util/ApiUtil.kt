package com.flappy.wanandroid.util

import com.flappy.wanandroid.api.ApiException
import com.flappy.wanandroid.api.ApiResponse

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:58 2022/9/6
 */
suspend fun <T : Any> safeApiCall(call: suspend () -> ApiResponse<T>): Result<T> {
    return Result.runCatching {
        val response = call.invoke()
        if (response.isSuccess || null != response.data) {
            return success(response.data!!)
        }
        return failure(ApiException(response.errorCode, response.errorMsg))
    }
}