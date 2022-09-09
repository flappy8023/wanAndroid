package com.flappy.wanandroid.repository

import com.flappy.wanandroid.api.ApiException
import com.flappy.wanandroid.api.ApiResponse


/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:21 2022/9/6
 */
open class BaseRepository {
    open suspend fun <T : Any> apiCall(call: suspend () -> ApiResponse<T>): Result<T> {
        return Result.runCatching {
            val response = call.invoke()
            if (response.isSuccess || null != response.data) {
                response.data!!
            }
            return failure(ApiException(response.errorCode, response.errorMsg))
        }
    }
}