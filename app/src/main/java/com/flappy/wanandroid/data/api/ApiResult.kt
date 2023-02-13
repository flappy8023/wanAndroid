package com.flappy.wanandroid.data.api

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 13:10 2022/9/7
 */
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val exception: ApiException) : ApiResult<Nothing>()
}
